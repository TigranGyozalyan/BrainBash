$(".menu-toggle").on('click', function () {
    $(this).toggleClass("on");
    $('.menu-section').toggleClass("on");
    $("nav ul").toggleClass('hidden');
});

$('.sub-menu ul').hide();
$(".sub-menu a").click(function () {
    $(this).parent(".sub-menu").children("ul").slideToggle("100");
    $(this).find(".right").toggleClass("fa-caret-up fa-caret-down");
});


let topics;

$.get('/topic/all', function (categories) {
    topics = categories;
});

let subcategories;

$.get('/category', function (categories) {
    loadCategories(categories);
});


$.get('/subcategory', function (categories) {
    subcategories = categories;
});

let categoryChecked = [];
let subcategoryChecked = [];

function loadCategories(array) {
    let main = $("#categoryMenu");

    console.log(array);
    for (let i = 0; i < array.length; i++) {
        main.append($("<li>")
            .append("<a href='#" + array[i].id + "'><span>" + array[i].type + "</span> <input type='checkbox' name='cat' id='" + i + "' ></a>")
            .append()
            .append("</li>"));
    }

}

$().ready(function () {
    $('#editOptions').click(function () {
        categoryChecked.length = 0;
        $.each($("input[name='cat']:checked"), function () {

            categoryChecked.push($(this).attr("id"));
            console.log(categoryChecked);
        });

        let main = $("#subcategoryMenu");
        main.empty();
        main.append($("<button name='edit' id='edit'>Select </button>"));
        for (let i = 0; i < subcategories.length; i++) {
            for (let j = 0; j < categoryChecked.length; j++) {

                // console.log(parseInt(categoryChecked[j])+1+" "+subcategories[i].categoryId);

                if (parseInt(categoryChecked[j]) + 1 === subcategories[i].categoryId) {
                    main
                        .append($("<li>")
                            .append("<a href='#" + subcategories[i].id + "'><span>" + subcategories[i].typeName + "</span> <input type='checkbox' name='sub' id='" + i + "' ></a>")
                            .append()
                            .append("</li>"));

                }
            }
        }

        $('#edit').click(function () {
            subcategoryChecked.length = 0;
            $.each($("input[name='sub']:checked"), function () {

                subcategoryChecked.push($(this).attr("id"));

            });

            let topic = $("#topicMenu");

            topic.empty();
            for (let i = 0; i < topics.length; i++) {
                for (let j = 0; j < subcategoryChecked.length; j++) {
                    if (parseInt(subcategoryChecked[j]) + 1 === topics[i].subCategoryId) {
                        topic
                            .append($("<li>")
                                .append("<a href='/test/menu/" + topics[i].id + "'><span>" + topics[i].topicName + "</span></a>")
                                .append()
                                .append("</li>"));

                    }
                }
            }

        });
    });
});

consoleText(['Let\'s Check Your Brain.', 'Choose The Test.', 'Click The Button On The Right Side.'], 'text', ['white', 'rebeccapurple', 'orange']);

function consoleText(words, id, colors) {
    if (colors === undefined) colors = ['#fff'];
    let visible = true;
    let con = document.getElementById('console');
    let letterCount = 1;
    let x = 1;
    let waiting = false;
    let target = document.getElementById(id);
    target.setAttribute('style', 'color:' + colors[0]);
    window.setInterval(function () {

        if (letterCount === 0 && waiting === false) {
            waiting = true;
            target.innerHTML = words[0].substring(0, letterCount);
            window.setTimeout(function () {
                let usedColor = colors.shift();
                colors.push(usedColor);
                let usedWord = words.shift();
                words.push(usedWord);
                x = 1;
                target.setAttribute('style', 'color:' + colors[0]);
                letterCount += x;
                waiting = false;
            }, 1000)
        } else if (letterCount === words[0].length + 1 && waiting === false) {
            waiting = true;
            window.setTimeout(function () {
                x = -1;
                letterCount += x;
                waiting = false;
            }, 1000)
        } else if (waiting === false) {
            target.innerHTML = words[0].substring(0, letterCount);
            letterCount += x;
        }
    }, 120);
    window.setInterval(function () {
        if (visible === true) {
            con.className = 'console-underscore hidden';
            visible = false;

        } else {
            con.className = 'console-underscore';

            visible = true;
        }
    }, 400)
}




