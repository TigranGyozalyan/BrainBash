$.post('/user/role', function (user) {
    action(user)
});

let hidden = true;

function action(user) {
    console.log(user);
    for (let i = 0; i < user.role.length; i++) {
        if (user.role[i] === "ADMIN") {
            hidden = false;
        }
    }
    if (!hidden) {
        let main = $('#adminButton');
        main.attr('class', 'container');
        main.append($("<a href='/user/admin' id='toggle' class='underlineDecor'>")
            .append("<span class='pulse-button'>Admin</span>")
            .append("</a>"));

    }
}

$('.sub-menu ul').hide();
$(".sub-menu a").click(function () {
    $(this).parent(".sub-menu").children("ul").slideToggle("100");
    $(this).find(".right").toggleClass("fa-caret-up fa-caret-down");
});