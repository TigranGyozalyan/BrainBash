let $selectDropDown = $('#categories');
let pathArray = window.location.pathname.split('/');
let id = parseInt(pathArray[pathArray.length - 1]);
let initialTest;
let initialTopicList = [];
let initialSubCategoryList = [];
let initialCategory;
let initialQuestionList;
let injectedSubCats = 0;
let injectedTopics = 0;
let injectedQuestions = 0;
$.get(/test/ + id, function (testData) {
    $('#test_name').val(testData.test_name);
    $('#test_description').val(testData.description);
    $('#duration').val(testData.duration);
    initialTest = testData;
    initialQuestionList = initialTest.questionIds;
    console.log('the question list is : ' + initialQuestionList);
    let topicPromise;
    $.each(initialQuestionList, function () {
        topicPromise = $.when(topicPromise, $.get('/question/' + this, function (initialQuestion) {
            let topicId = initialQuestion.topicId;
            if (initialTopicList.indexOf(topicId) === -1)
                initialTopicList.push(topicId);
            // console.log(initialTopicList);
        }));
    });
    topicPromise.done(function () {
        let subCategoryPromise;
        $.each(initialTopicList, function () {
            // console.log(initialTopicList);
            subCategoryPromise = $.when(subCategoryPromise, $.get('/topic/' + this, function (initialTopic) {
                let subCategoryId = initialTopic.subCategoryId;
                if (initialSubCategoryList.indexOf(subCategoryId) === -1)
                    initialSubCategoryList.push(subCategoryId);
            }));

        });
        subCategoryPromise.done(function () {
            let categoryPromise;
            let initSubCategoryId = initialSubCategoryList[0];
            categoryPromise = $.get('/subcategory/' + initSubCategoryId, function (initialSubCategory) {
                initialCategory = initialSubCategory.categoryId;
            });
            categoryPromise.done(function () {
                $('#categories').val(initialCategory).change();
            });
        });
    });
});


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
                                                if (injectedQuestions !== initialTopicList.length) {
                                                    console.log('iteration number ' + injectedQuestions);
                                                    loadInitialQuestionSelections();
                                                    injectedQuestions++;
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
                            if (injectedTopics !== initialTopicList.length) {
                                loadInitialTopicSelections();
                                injectedTopics++;
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
        });
        if (injectedSubCats !== initialSubCategoryList.length) {
            loadInitialSubCategorySelections();
            injectedSubCats++;
        }
    });
}

function loadInitialSubCategorySelections() {
    initialSubCategoryList.forEach(function (subCategoryId) {
        $("[subcategoryId=" + subCategoryId + "]").click();
    });
}

function loadInitialTopicSelections() {
    initialTopicList.forEach(function (topicId) {
        let selector = $("[topicId=" + topicId + "]");
        if (!selector.is(":checked"))
            selector.click();
    });
}


function loadInitialQuestionSelections() {
    initialQuestionList.forEach(function (initialQuestionId) {
        $("[questionId=" + initialQuestionId + "]").prop('checked', true);
        console.log('checked question with id ' + initialQuestionId);
    })
}


function loadSelectOptionsCategories($select, categories) {
    // $select.children().remove();
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
        url: "/test/update/" + id,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: (function () {
            alert('Question was successfully created');
        })
    })
}

