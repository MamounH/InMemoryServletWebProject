
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


<style>


    .img-container {
        text-align: center;
        display: block;
    }


    body {
        color: #000;
        overflow-x: hidden;
        height: 100%;
        background: #0264d6; /* Old browsers */
        background: -moz-radial-gradient(center, ellipse cover,  #0264d6 1%, #1c2b5a 100%); /* FF3.6+ */
        background: -webkit-gradient(radial, center center, 0px, center center, 100%, color-stop(1%,#0264d6), color-stop(100%,#1c2b5a)); /* Chrome,Safari4+ */
        background: -webkit-radial-gradient(center, ellipse cover,  #0264d6 1%,#1c2b5a 100%); /* Chrome10+,Safari5.1+ */
        background: -o-radial-gradient(center, ellipse cover,  #0264d6 1%,#1c2b5a 100%); /* Opera 12+ */
        background: -ms-radial-gradient(center, ellipse cover,  #0264d6 1%,#1c2b5a 100%); /* IE10+ */
        background: radial-gradient(ellipse at center,  #0264d6 1%,#1c2b5a 100%); /* W3C */
        background-repeat: no-repeat
    }

    input,
    textarea {
        background-color: #F3E5F5;
        border-radius: 50px !important;
        padding: 12px 15px 12px 15px !important;
        width: 100%;
        box-sizing: border-box;
        border: none !important;
        border: 1px solid #F3E5F5 !important;
        font-size: 16px !important;
        color: #000 !important;
        font-weight: 400
    }

    input:focus,
    textarea:focus {
        -moz-box-shadow: none !important;
        -webkit-box-shadow: none !important;
        box-shadow: none !important;
        border: 1px solid #D500F9 !important;
        outline-width: 0;
        font-weight: 400
    }

    button:focus {
        -moz-box-shadow: none !important;
        -webkit-box-shadow: none !important;
        box-shadow: none !important;
        outline-width: 0
    }

    .card {
        border-radius: 0;
        border: none
    }

    .card1 {
        width: 50%;
        padding: 40px 30px 10px 30px
    }

    .card2 {
        width: 50%;
        background-image: linear-gradient(to right, #0264d6, #1c2b5a)
    }

    #logo {
        width: 300px;
        height: 100px
    }

    #logo1 {
        width: 300px;
        height: 100px
    }

    .heading {
        margin-bottom: 60px !important
    }

    ::placeholder {
        color: #000 !important;
        opacity: 1
    }

    :-ms-input-placeholder {
        color: #000 !important
    }

    ::-ms-input-placeholder {
        color: #000 !important
    }

    .form-control-label {
        font-size: 12px;
        margin-left: 15px
    }

    .msg-info {
        padding-left: 15px;
        margin-bottom: 30px
    }

    .btn-color {
        border-radius: 50px;
        color: #ffffff;
        background-image: linear-gradient(to right, #1c2b5a, #0264d6);
        padding: 15px;
        cursor: pointer;
        border: none !important;
        margin-top: 40px
    }

    .btn-color:hover {
        color: #fff;
        background-image: linear-gradient(to right, #1c2b5a, #d40a0a)
    }

    .btn-white {
        border-radius: 50px;
        color: #D500F9;
        background-color: #fff;
        padding: 8px 40px;
        cursor: pointer;
        border: 2px solid #D500F9 !important
    }

    .btn-white:hover {
        color: #fff;
        background-image: linear-gradient(to right, #FFD54F, #D500F9)
    }

    a {
        color: #000
    }

    a:hover {
        color: #000
    }

    .bottom {
        width: 100%;
        margin-top: 50px !important
    }

    .sm-text {
        font-size: 15px
    }

    @media screen and (max-width: 992px) {
        .card1 {
            width: 100%;
            padding: 40px 30px 10px 30px
        }

        .card2 {
            width: 100%
        }

        .right {
            margin-top: 100px !important;
            margin-bottom: 100px !important
        }
    }

    @media screen and (max-width: 768px) {
        .container {
            padding: 10px !important
        }

        .card2 {
            padding: 50px
        }

        .right {
            margin-top: 50px !important;
            margin-bottom: 50px !important
        }
    }
</style>


<form action="/login" method="post">
<div class="container px-4 py-5 mx-auto">
    <div class="card card0">

        <div class="d-flex flex-lg-row flex-column-reverse">

            <div class="card card1">
                <div class="row justify-content-center px-3 mb-3"> <img id="logo" src="https://raw.githubusercontent.com/MamounH/Logo/main/Library1.png"> </div>
                <div class="row justify-content-center my-auto">
                    <div class="col-md-8 col-10 my-5">
                        <p style="color: darkred; font-size: 16px;"
                           > ${errorM}</p>
                        <p style="color: green; font-size: 16px;"
                           > ${logout} </p>

                        <div class="form-group"> <label class="form-control-label text-muted">Email</label> <input type="text" id="email" name="email" placeholder="Email" class="form-control" required> </div>
                        <div class="form-group"> <label class="form-control-label text-muted">Password</label> <input type="password" id="password" name="password" placeholder="Password" class="form-control" required> </div>
                        <div class="row justify-content-center my-3 px-3"> <input class="btn-block btn-color" type="submit" value="Sign In" > </div>
                    </div>
                </div>
            </div>
            <div class="card card2">
                <div class="my-auto mx-md-5 px-md-5 right">
                 <span class="img-container">
                    <h4 style="text-align: justify" class="text-white">test "Anyone who stops learning is old, whether at twenty or eighty. Anyone who keeps learning stays young. The greatest thing in life is to keep your mind young." -Henry Ford </h4>
                     <span class="img-container">
                     <img id="logo1" src="https://raw.githubusercontent.com/MamounH/Logo/main/divider.png"  />
                    </span>

                    <img src="https://raw.githubusercontent.com/MamounH/Logo/main/AtyponLogo-RemovedBG.png"  />
                    </span>

                </div>
            </div>
        </div>
    </div>
</div>

</form>
