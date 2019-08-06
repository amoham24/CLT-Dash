<!DOCTYPE html>
<html>
    <head>
        <meta charset = "utf-8"/>
        <title>CLT-Dash Login</title>
        <link rel="stylesheet" href="main.css">
        <meta name="google-signin-client_id" content="1054208658188-uunejfl6ji6ne2a9ngs80d2u598ldjh1.apps.googleusercontent.com">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="signIn.js"></script>
    </head>
    <body class="background">
        <div class="center">
            <div class="banner">
                <img src="uncclogo2.png" alt="banner art" class="small_logo"/>
                </br>
                <img src="logo.png" alt="banner art" id="top_logo"/>
                <h1>CLT-Dash : Login</h1>
            </div>

            <div class="body_text">
                Welcome to CLT-Dash, your UNC Charlotte Campus-Wide Scavenger Hunt, brought to you by SOAR! Please enter your UNC Charlotte e-mail and password below to begin exploring! 
                <br><strong>${loginmessage}</strong>
            </div>

            <div>
                                <form action="Controller?page=scanner" method="POST" hidden>
                                    <fieldset class="entry">
                                        <input id="email" type="email" name="email" value="" placeholder="UNCC E-mail">
                                        <input id="submit" type="submit" value="Submit" class="button">
                                        <input type="text" name="page" value="scanner" hidden>
                                        <input type="text" name="action" value="login" hidden>
                                    </fieldset>
                                </form>
                <div class="g-signin2" data-onsuccess="onSignIn" class="button"></div>
            </div>
        </div>
    </body>
</html>