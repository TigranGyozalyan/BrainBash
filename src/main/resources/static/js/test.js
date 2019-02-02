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

    $.get('/subcategory?categoryId=' + categoryId, function (subCategories) {

        $subCategoryDiv.empty();
        subCategories.forEach(function (subCategory) {
            let subCategoryLabel = $('<label/>').html(subCategory.typeName);
            let subCategoryCheckBox = $('<input/>').attr({
                type: 'checkBox'
            }).attr('subCategoryId', subCategory.id)
                .on('click', function () {
                    if ($(this).is(":checked")) {
                        let subCategoryId = subCategoryCheckBox.attr('subCategoryId');
                        let $topicDiv = $('<Div/>').attr('id', 'topicsDiv').attr('class', 'topicNavigation');
                        $.get('/topic?subCategoryId=' + subCategoryId, function (topics) {
                            console.log(topics);
                            if (!($.isEmptyObject(topics))) {
                                topics.forEach(function (topic) {
                                    let topicLabel = $('<label/>').html('   ' + topic.topicName);
                                    let topicCheckBox = $('<input/>').attr({
                                        type: 'checkbox'
                                    }).attr('topicId', topic.id);

                                    $topicDiv.prepend('<br/>');
                                    $topicDiv.prepend(topicLabel.prepend(topicCheckBox));


                                });
                            } else {
                                alert('topic is empty');
                            }
                        });
                        subCategoryLabel.append($topicDiv);
                    } else {
                        let $topicDiv = $('#topicsDiv');
                        $topicDiv.remove();
                    }
                });

            $subCategoryDiv.prepend($('<br/>'));
            $subCategoryDiv.prepend(subCategoryLabel.prepend(subCategoryCheckBox));
        })
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




