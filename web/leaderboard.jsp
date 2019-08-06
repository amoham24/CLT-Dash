<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset = "utf-8"/>
        <title>CLT-Dash : Leaderboard</title>
        <link rel="stylesheet" href="main.css">
    </head>
    <a href="https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=https://cltdash.com/Controller?page=logout"><img src="logout.png" alt="button" width="150px"></a>
    <body class="background">
        <div class="center">
            <div class="banner">
                <img src="logo.png" alt="banner art" class="small_logo"/>
                <h1>CLT-Dash : Leaderboard</h1>
            </div>
            <table class="center">
                <tr><th>Rank</th><th>Player</th><th>R1</th><th>R2</th><th>R3</th><th>R4</th><th>R5</th><th>Score</th></tr>
                        <c:forEach items = "${top10}" var = "i">
                    <tr <c:if test = "${i.getRank() == profile.getRank()}">
                            id="theUser"
                    </c:if>>
<!--                DON'T ERASE THIS LESS THAN-->
                    
                        
                        <td>${i.getRank()}</td>
                        <td>${i.getUserName()}</td>
                        <c:forEach items = "${i.getTimes()}" var = "j">
                            <td>${j}</td>
                        </c:forEach>
                        <td>${i.getScore()}</td></tr>
                    </c:forEach>
                    

            </table>
            <br>
            <table class="center">
                <tr><td>${profile.getRank()}</td><td>${profile.getUserName()}</td>
                    <c:forEach items = "${profile.getTimes()}" var = "x">
                        <td>${x}</td>
                    </c:forEach>
                    <td>${profile.getScore()}</td></tr>
            </table>
            <br><br>
            <a href="Controller?page=scanner"><img src="qr-back.png" alt="button" width="250px"></a>   
            <a href="Controller?page=map"><img src="map-for.png" alt="button" width="250px"></a>

        </div>
    </body>
</html>
