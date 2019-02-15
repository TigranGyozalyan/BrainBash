let questionCount = 0;
let wrapperDiv = $('#wrapper');
let interval;
let $wrapper;

let current;
let end;

wrapperDiv.ready(function () {
    $wrapper = $('#wrapper');

    let id = parseInt($('#testId').val());
    console.log(id);
    $.get('/question/test?testId=' + id, function (questions) {

        questions.forEach(function (question) {
            let $questionDiv = $('<div/>').attr('class', 'question').attr('id', question.id);
            questionCount++;

            let points = question.points;


            let $questionHeadLine = $('<div/>').attr('class', 'question-headline').text(questionCount + '. ' + question.question + ' (' + points + ' points)');
            let answers = question.answerDtoList;
            let correctAnswerCount = correct_answer_count(answers);
            let $answerContainer = $('<div/>').attr('class', 'question-answers');
            answers.forEach(function (answer) {
                let $label = $('<label/>');
                let $answerDiv = $('<div/>').attr('class', 'answer');
                $label.html(answer.answer);
                if (correctAnswerCount === 1) {
                    let $input = $('<input/>').attr('type', 'radio').attr('name', 'question' + questionCount).attr('answerId', answer.id);
                    $label.prepend($input);
                } else {
                    let $input = $('<input/>').attr('type', 'checkbox').attr('answerId', answer.id);
                    $label.prepend($input);
                }
                $answerDiv.append($label);
                $answerContainer.append($answerDiv);
            });
            $questionDiv.append($questionHeadLine);
            $questionDiv.append($answerContainer);
            $wrapper.append($questionDiv);
        });
        let $submit = $('<button/>').attr('class', 'btn-check').attr('onclick', 'submitTest()').html('Submit').attr('id', 'submitId');
        //  let $send=$('<form>').attr('action','http://localhost:8080/test/scorepage').attr('method','get');
        let $score = $('<button />').attr('class', 'btn-check').attr('onclick', "sendData();location.href='http://localhost:8080/test/scorepage'").attr('value', 'Go to Score').html('Go to Score').attr('id', 'scoreId');
        //    let $sendFinish=$('</form>');
        $score.prop('disabled', true);
        $score.hide();
        // $wrapper.append($send);
        $wrapper.append($submit);
        ///  $wrapper.append($sendFinish);
        $wrapper.append($score);

        $.post('/test/timer/' + id, function (timerData) {

            let endTime = new Date(timerData.endTime).getTime();

            $('#timer').append($('<span/>').attr('id', 'hours')).append($('<span/>').attr('id', 'minutes')).append($('<span/>').attr('id', 'seconds'));

            let now = new Date(timerData.currentTime).getTime();

            interval = setInterval(function () {

                let duration = endTime - now;

                let hours = (duration > 0) ? Math.floor((duration % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)) : 0;
                let minutes = (duration > 0) ? Math.floor((duration % (1000 * 60 * 60)) / (1000 * 60)) : 0;
                let seconds = (duration > 0) ? Math.floor((duration % (1000 * 60)) / 1000) : 0;
                $('#hours').text(hours);
                $('#minutes').text(minutes);
                $('#seconds').text(seconds);
                now = now + 1000;


                current = timerData.currentTime;
                end = now;

                if (duration < 1000) {

                    submitTest();
                }

            }, 1000);


        });


    });
});


function correct_answer_count(array) {
    let count = 0;
    array.forEach(function (element) {
        if (element.correct)
            count++;
    });
    return count;
}

function submitTest() {
    $wrapper.css("pointer-events", "none");
    $('.btn-check').css("pointer-events", "auto");
    clearInterval(interval);
    let rawData = $('.question').map(function () {
        let questionId = parseInt($(this).attr('id'));

        // $('#submission').attr('action','http://localhost:8080/').attr('method','POST');
        let answers = $(this).find('[answerId]').map(function () {
            if ($(this).is(':checked')) {
                return parseInt($(this).attr('answerId'));
            }
        }).toArray();

        return {
            questionId: questionId,
            chosenAnswerList: answers
        }
    }).toArray();

    let data = JSON.stringify(rawData);
    console.log(data);


    $.ajax({
        type: "POST",
        data: data,
        url: "/test/process",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: (function () {

        })
    });


    $('#submitId').remove();
    $('#scoreId').prop('disabled', false).show();


}


function sendData() {
    let timeData = JSON.stringify({
        currentTime: parseInt(current),
        endTime: parseInt(end)

    });
    console.log(timeData);

    $.ajax({
        type: "POST",
        data: timeData,
        url: "/history/test/update",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: (function () {

        })
    });

}

// Disable Back Button
history.pushState(null, null, location.href);
window.onpopstate = function () {
    history.go(1);
};
