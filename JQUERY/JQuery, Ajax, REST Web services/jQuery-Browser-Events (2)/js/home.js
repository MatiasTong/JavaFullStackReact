function hide() {
    $("#akronInfoDiv").hide()
    $("#minneapolisInfoDiv").hide()
    $("#louisvilleInfoDiv").hide()
    $("#mainInfoDiv").show()
}

$(document).ready(function () {
    hide()

    $("#mainButton").on("click", function () {
        $("#akronInfoDiv").hide()
        $("#minneapolisInfoDiv").hide()
        $("#louisvilleInfoDiv").hide()

        $("#mainInfoDiv").show()

    })

    $("#akronButton").on("click", function () {
        hide()

        $("#mainInfoDiv").toggle()
        $("#akronInfoDiv").toggle()
        $("#akronWeather").hide()
    })

    $("#minneapolisButton").on("click", function () {
        hide()
        $("#mainInfoDiv").toggle()
        $("#minneapolisInfoDiv").toggle()
        $("#minneapolisWeather").hide()
    })

    $("#louisvilleButton").on("click", function () {
        hide()
        $("#mainInfoDiv").toggle()
        $("#louisvilleInfoDiv").toggle()
        $("#louisvilleWeather").hide()
    })

    $("#akronWeatherButton").on("click",
        function () {
            $("#akronWeather").toggle()
        })

    $("#louisvilleWeatherButton").on("click",
        function () {
            $("#louisvilleWeather").toggle()
        })

    $("#minneapolisWeatherButton").on("click",
        function () {
            $("#minneapolisWeather").toggle()
        })


});