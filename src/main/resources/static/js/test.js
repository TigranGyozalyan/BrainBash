let $selectDropDown = $('#categories');
$.get('/category', function (categories) {
    loadCategories(categories);
});

function loadCategories(categories) {
    loadSelectOptionsCategories($selectDropDown, categories);
}

function loadSubCategories() {
    $('#subCategoryDiv').remove();
    let categoryId = $selectDropDown.val();
    let $subCategoryDiv = $('<Div/>').attr('id', 'subCategoryDiv').attr('class', 'subCategoryNavigation');
    $('.navigation').append($subCategoryDiv);
    $.get('/subcategory/filter?categoryId=' + categoryId, function (subCategories) {
        console.log(subCategories);
        $subCategoryDiv.empty();
        subCategories.forEach(function (subCategory) {
            console.log(subCategory.typeName);
            let subCategoryLabel = $('<label/>').html(subCategory.typeName);
            let subCategoryCheckBox = $('<input/>').attr({
                type: 'checkBox'
            }).attr('subCategoryId', subCategory.id)
                .on('click', function () {
                    if ($(this).is(":checked")) {
                        let subCategoryId = subCategoryCheckBox.attr('subCategoryId');
                        let $topicDiv = $('<Div/>').attr('id', 'topicsDiv' + subCategoryId).attr('class', 'topicNavigation');
                        $.get('/topic?subCategoryId=' + subCategoryId, function (topics) {
                            console.log(topics);
                            if (!($.isEmptyObject(topics))) {
                                topics.forEach(function (topic) {
                                    let topicLabel = $('<label/>').html(topic.topicName);
                                    let topicCheckBox = $('<input/>').attr({
                                        type: 'checkbox'
                                    }).attr('topicId', topic.id);
                                    $topicDiv.prepend('<br/>');
                                    $topicDiv.prepend(topicLabel.prepend(topicCheckBox));
                                    topicCheckBox.on('click', function () {
                                        if ($(this).is(':checked')) {
                                            let topicId = $(this).attr('topicId');
                                            let $questionsDiv = $('<div/>').attr('class', 'questionSpace').attr('topicId', topicId);
                                            $questionsDiv.append($('<br/>')).append($('<label/>').html(topic.topicName));
                                            $.get('/question?topicId=' + topicId, function (questions) {
                                                console.log(questions);
                                                if (!($.isEmptyObject(questions))) {
                                                    questions.forEach(function (question) {
                                                        console.log(question);
                                                        let $questionDiv = $('<div/>').attr('topicId', question.topicId).attr('class', 'questionStyle');
                                                        let $questionLabel = $('<label/>').html(question.question);
                                                        let $questionCheckBox = $('<input/>').attr({
                                                            type: 'checkbox'
                                                        }).attr('questionId', question.id).attr('class', 'questionInputClass').attr('topicId', question.topicId).attr('questionId', question.id);
                                                        $questionLabel.prepend($questionCheckBox);
                                                        $questionDiv.append($questionLabel);
                                                        $questionsDiv.append($questionDiv);
                                                    });
                                                    $topicDiv.append($questionsDiv);
                                                } else {
                                                    alert(topic.topicName + ' is empty');
                                                    topicCheckBox.prop('checked', false);
                                                }
                                            });
                                        } else {
                                            let topicId = $(this).attr('topicId');
                                            let selector = $("[class =questionSpace][topicId=" + topicId + "]");
                                            let $questionDivToRemove = $topicDiv.find(selector);
                                            $questionDivToRemove.remove();
                                        }
                                    })
                                });
                            } else {
                                alert('topic is empty');
                                alert(subCategory.typeName + ' is empty');
                                subCategoryCheckBox.prop('checked', false);
                            }
                        });
                        subCategoryLabel.append($topicDiv);
                    } else {
                        let $topicDiv = $('#topicsDiv' + subCategory.id);
                        $topicDiv.remove();
                    }
                });
            $subCategoryDiv.prepend($('<br/>'));
            $subCategoryDiv.prepend(subCategoryLabel.prepend(subCategoryCheckBox));
        })
    })
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

function createTest() {

    let questionIdList = $("input:checkbox[class=questionInputClass]:checked").map(function () {
        let id = parseInt($(this).attr('questionId'));
        return id;
    }).toArray();
    let duration = parseInt($('#duration').val());
    let test_name = $('#test_name').val();
    let description = $('#test_description').val();
    let test = JSON.stringify({
        duration: duration,
        test_name: test_name,
        description: description,
        questionIds: questionIdList
    });
    JSON.stringify(test);
    console.log(test);
    $.ajax({
        type: "POST",
        data: test,
        url: "/test/add",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: (function () {
            alert('Question was successfully created');
        })
    })
}

