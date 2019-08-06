<!DOCTYPE html>
<html>
    <head>
        <meta charset = "utf-8"/>
        <title>CLT-Dash : QR</title>
        <link rel="stylesheet" href="main.css">

        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.10/vue.min.js"></script>
        <script type="text/javascript" src="https://webpages.uncc.edu/amoham24/instascan.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script type="text/javascript" src="parser.js"></script>
        <c:choose>
            <c:when test = "${os == null}">
                <script type="text/javascript" src="OScheck.js"></script>
            </c:when>

        </c:choose>


    </head>
    <a href="https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=https://cltdash.com/Controller?page=logout"><img src="logout.png" alt="button" width="150px"></a>
    <body class="background">

        <div class="center">
            <div class="banner">
                <img src="logo.png" alt="banner art" class="small_logo"/>
                <h1>CLT-Dash : Scavenger Hunt Scanner</h1>
            </div>
            <h3>${message}</h3>

            <c:choose>


                <c:when test="${os.equals('IOS')}">

                    <div id="app" class="video_style">
                        <meta name="apple-mobile-web-app-capable" content="yes">
                        <video width="100%" id="scanner" class="video-back" playsinline></video>     
                    </div>
                    <script id = "theApp" type="text/javascript" src="appIOS.js"></script>
                </c:when>

                <c:when test="${os.equals('default')}">
                    <div id="app">
                        <!-- only HTML differnce is that Android users have this list where they can change their camera -->
                        <div class="sidebar">
                            <section class="cameras">
                                <ul>
                                    <li v-if="cameras.length === 0" class="empty">No cameras found</li>
                                    <li v-for="camera in cameras">
                                        <span v-if="camera.id == activeCameraId" :title="formatName(camera.name)" class="active">{{ formatName(camera.name) }}</span>
                                        <span v-if="camera.id != activeCameraId" :title="formatName(camera.name)">
                                            <a @click.stop="selectCamera(camera)">{{ formatName(camera.name) }}</a>
                                        </span>
                                    </li>
                                </ul>
                            </section>
                        </div>
                        <!-- End of Camera list, everything else is close to the same -->
                        <div class="video_style">
                            <video width="100%" id="preview" class="video-back"></video>
                        </div>

                    </div>
                    <script id="theApp" type="text/javascript" src="app.js"></script>
                </c:when>
            </c:choose>

            <h4>${qrmessage}</h4>
            <c:choose>

                <c:when test = "${riddle == null}">
                    <h2 id="title">Riddle #1</h2>
                    <div id = "riddle" class="body_text">This riddle will lead to you to the next location on your QR code scavenger hunt!</div></br>

                </c:when>


                <c:otherwise>
                    <h2 id="title">${riddle.getTitle()}</h2>
                    <div id = "riddle" class="body_text">${riddle.getRiddle()}</div></br>

                </c:otherwise>
            </c:choose>
                    
            <c:if test="${isAdmin == true}">
                <a href="Controller?page=edit">Edit the QR Codes</a>
            </c:if>
                <c:choose>

                <c:when test = "${done}">
                    <a onclick="redo()" class="button">REDO</a> 
                </c:when>

                <c:otherwise>
                    
                    <a onclick="skip()"><img src="skip.png" alt="button" width="250px"></a> 
                </c:otherwise>
            </c:choose>
                
                
             
            <form id="QRsubmission" action="Controller?page=scanner" method="POST" hidden>
                <input type="text" name="advance" value="" id="qrInTake">
                <input type="submit" id="submit">
            </form>
                
                <form id="skip" action="Controller?page=scanner" method="POST" hidden>
                <input id="action" type="text" name="action" value="" >
                <input type="submit" id="actionSubmit">
            </form>
           
            <a href="Controller?page=leaderboard"><img src="lead-for.png" alt="button" width="250px"></a>
            <a href="Controller?page=map"><img src="map-for.png" alt="button" width="250px"></a>
        </div>
    </body>
</html>
