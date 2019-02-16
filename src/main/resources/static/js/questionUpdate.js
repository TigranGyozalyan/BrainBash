let pathArray = window.location.pathname.split('/');
let id = parseInt(pathArray[pathArray.length - 1]);
let initialQuestion;
let initialTopicId;
let initialCategoryId;
let initialSubCategoryId;

$.get('/question/' + id, function (data) {
    initialQuestion = data;
    initialTopicId = data.topicId;
    $.get('/topic/' + initialQuestion.topicId, function (topicData) {
        initialSubCategoryId = topicData.subCategoryId;
        $.get('/subcategory/' + initialSubCategoryId, function (subData) {


            $('#points').val(initialQuestion.points);
            $('#questionText').val(initialQuestion.question);
            $('#level').val(initialQuestion.level);

            initialQuestion.answerDtoList.forEach(function (answer) {
                addAnswer();
                $('#answerText' + answerCount).val(answer.answer);
                $('#answerDescription' + answerCount).val(answer.description);
                if (answer.correct) {
                    $('#isCorrect' + answerCount).prop('checked', true);
                }
            });

            initialSubCategoryId = subData.id;
            initialCategoryId = subData.categoryId;

            $('#categories').val(initialCategoryId);
            $.when(loadSubCategoriesDropDown()).done(function f() {

                $('#subCategories').val(initialSubCategoryId);
                console.log(initialSubCategoryId);
                $.when(loadTopicsDropDown()).done(function () {
                    $('#topics').val(initialTopicId);
                    console.log(initialTopicId);
                });
            });
        });
    });
});


let $selectDropDown = $('#categories');

$.get('/category', function (categories) {
    loadCategories(categories);
});

function loadCategories(categories) {
    loadSelectOptionsCategories($selectDropDown, categories);
}

function loadSelectOptionsCategories($select, categories) {
    categories.forEach(function (category) {
        let $option = $('<option/>', {
            value: category.id,
            text: category.type
        });
        $select.append($option);
    });

    $select.val([]);
}

function loadSubCategoriesDropDown() {
    let def = $.Deferred();
    $('#subCategoryDiv').empty();
    let categoryId = $('#categories').val();
    $.get('/subcategory/filter?categoryId=' + categoryId, function (subCategories) {

        let $subCategoryDiv = $('<div/>').attr('id', 'subCategoryDiv');

        let $subCategorySelector = $('<select/>').attr('id', 'subCategories');

        subCategories.forEach(function (subCategory) {
            let $option = $('<option/>', {
                value: subCategory.id,
                text: subCategory.typeName
            });
            $subCategorySelector.append($option);
        });
        let subLabel = $('<label/>').html(' SubCategory: ');
        subLabel.append($subCategorySelector);
        $('.navigation').append($subCategoryDiv);
        $('#subCategoryDiv').append($('<br/>')).append(subLabel);
        $subCategorySelector.val([]);
        $subCategorySelector.attr('onchange', 'loadTopicsDropDown()');
        console.log('finished loading subCatSelector');
        def.resolve();
    });
    return def.promise();
}

function loadTopicsDropDown() {
    let def = $.Deferred();
    $('#topicDiv').empty();
    let subCategoryId = $('#subCategories').val();
    $.get('/topic?subCategoryId=' + subCategoryId, function (topics) {
        let $topicDiv = $('<div/>').attr('id', 'topicDiv');
        let $topicSelector = $('<select/>').attr('id', 'topics');

        topics.forEach(function (topic) {
            let $option = $('<option/>', {
                value: topic.id,
                text: topic.topicName
            });
            $topicSelector.append($option);
            $topicSelector.val([]);
        });

        let topicLabel = $('<label/>').html(' Topic: ');
        topicLabel.append($topicSelector);
        $('#subCategoryDiv').append($topicDiv);
        $('#topicDiv').append($('<br/>')).append(topicLabel);
        console.log('topic selector is loaded');
        def.resolve();

    });
    return def.promise();
}


let answerCount = 0;

function addAnswer() {

    answerCount++;

    let $answerBlock = $('#answerBlock');
    let $answerSet = $('<div/>').attr('class', 'answerClass');
    let answerText = $('<textarea />').attr('row', '5').attr('column', '25').attr('type', 'text').attr('id', 'answerText' + answerCount).attr('class', 'answerText');
    let answerDescription = $('<textarea />').attr('row', '5').attr('column', '25').attr('id', 'answerDescription' + answerCount).attr('class', 'description');
    let isCorrect = $('<input/>').attr('type', 'checkbox').attr('id', 'isCorrect' + answerCount).attr('class', 'isCorrect');
    let label = $('<label/>').html('Answer #' + answerCount + ': ');
    let descriptionLabel = $('<label/>').html(' Description: ');
    let isCorrectLabel = $('<label/>').html(' Is Correct ');

    $answerBlock.append($answerSet);
    $answerSet.append(answerText).append(descriptionLabel).append(answerDescription).append(isCorrect).append(label);
    $answerSet.prepend(label);
    $answerSet.append(isCorrectLabel);

}

function removeAnswer() {
    if (answerCount >= 1) {
        let divToRemove = $('.answerClass').last();
        divToRemove.remove();
        answerCount--;
    }
}


function UpdateQuestion() {
    let topicId = parseInt($('#topics').val());
    let questionText = $('#questionText').val();
    let level = $('#level').val();
    let points = parseInt($('#points').val());

    let localCount = -1;
    let answerData = $('#answerBlock').find('.answerClass').map(function () {
        localCount++;
        let answerText = $(this).find('.answerText').val();
        console.log(answerText);
        let answerDescription = $(this).find('.description').val();
        console.log(answerDescription);
        let isCorrect = $(this).find('.isCorrect').is(":checked");
        console.log(isCorrect);

        return {
            answer: answerText,
            description: answerDescription,
            correct: isCorrect
        }
    }).toArray();
    console.log(answerData);

    let question = JSON.stringify({
        topicId: topicId,
        question: questionText,
        level: level,
        points: points,
        answerDtoList: answerData
    });
    console.log(question);

    $.ajax({
        type: "POST",
        data: question,
        url: "/question/update/" + id,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    });
}

