$(document).ready(function () {
    $("h1").css("text-align","center")
    $("h2").css("text-align","center")
    $(".myBannerHeading").removeClass("myBannerHeading").addClass("page-Header")
    $("#yellowHeading").text("Yellow Team")
    $("#orangeHeading").css("backgroundColor", "orange")
    $("#blueHeading").css("backgroundColor", "blue")
    $("#redHeading").css("backgroundColor", "red")
    $("#yellowHeading").css("backgroundColor", "yellow")
    $("#yellowTeamList").append("<li>Joseph Banks</li><li>Simon Jones</li>")
    $("#oops").hide()
    $("#footerPlaceholder").remove()
    $("#footer").append("<p>Matias Tong matiastong@gmail.com</p>").css({"font-family":"Courier", "font-size":"24px"})
});