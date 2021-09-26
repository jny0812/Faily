$(document).ready(function(){
    $("#btnEmail").click(function(){

        $.ajax({
            type: "POST",
            url: "/SendEmail",
            data: {
                "e_mail" : $("#Email").val()
            },
            success: function(res){
                console.log("입력하신 이메일은 "+$("#Email").val());

                console.log("인증번호는 "+res+"입니")
            }
        })
    })

    var token;

    $("#btnlogin").click(function(){
        $.ajax({
            type: "GET",
            url: "/login",
            async : false,
            data: {
                "user_email" : $("#login_user_email").val(),
                "user_pw" : $("#login_user_pw").val()
            },

            success: function(res){
                console.log(res.result);
                if(res.result == "true"){
                    $.ajax({
                        type: "GET",
                        url: "/get/token",
                        async : false,
                        data: {
                            "subject" : $("#login_user_email").val(),
                        },
                        success: function(res){
                            console.log(res.result)
                        }
                    })
                }
                else{
                    alert("아이디나 비밀번호가 맞지않습니다.");
                }
            }
        })
    })

    $("#btnJoin").click(function(){

        $.ajax({
            type: "POST",
            url: "/joindo",
            data: {
                "user_email" : $("#user_email").val(),
                "user_pw" : $("#user_pw").val(),
                "user_name" : $("#user_name").val(),
                "user_bdate" : $("#user_bdate").val()
            },
            success: function(res){
                console.log(res);
                if(res == "false"){
                    alert("아이디나 비밀번호가 맞지않습니다.");
                }
                else{
                    alert("로그인성공!")
                }
            }
        })
    })
})