var forgetPwdConfirm = {
    idconfirmchk : false,
    emconfirmchk : false
}

$(document).ready(function () {

    var email;

    $("#checkEmail").click(function() {

        email = $("#memail").val();

        // 이메일 값을 가져온다.
        if( email === '') {
            alert("이메일을 입력해주세요.");
            $("#memail").focus();
        } else {
            // Fetch를 이용하여 요청을 보낸다.
            fetch("/auth/request-forget-verify-mail", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(email)
            })
                .then(response => {
                    if (!response.ok) {
                        alert("유효한 이메일이 아닙니다.\n입력한 이메일을 확인해주세요.")
                        throw new Error("Network response was not ok");
                    }
                    return response.json();
                })
                .then(data => {
                    if(data === null) {
                        return alert("존재하는 이메일이 없습니다.\n이메일을 다시 한 번 확인해주세요.")
                    }

                    var afterChkEmail =
                        "<div>"
                        +   "<h4 style=\"font-weight:bold; font-size: 10px; color: #6667AB\">"
                        +   "이메일로 전송 받은 인증 코드를 입력해주세요.<br>"
                        +   "제한 시간 초과 시 재전송 받아야해요."
                        +   "</h4>"
                        + "</div>"
                        + "<div class='email_box'>"
                        +   "<input class=\"login_input code\" type=\"text\" id=\"memailconfirm\" placeholder=\"인증번호를 입력해주세요\">"
                        +   "<span class='timer' id='timer'></span>"
                        + "</div>"
                        + "<button class=\"login_btn\" type=\"button\" id=\"memailconfirmTxt\">인증하기</button>"

                    $("#email_div").html(afterChkEmail);

                    var timeLimit = 300; // 제한 시간 (초)
                    var timerElement = $('#timer');
                    var inputElement = $('#memailconfirm');

                    var timer = setInterval(function() {
                        var minutes = Math.floor(timeLimit / 60);
                        var seconds = timeLimit % 60;

                        // 시간을 "mm:ss" 형식으로 표시
                        var formattedTime = ('0' + minutes).slice(-2) + ':' + ('0' + seconds).slice(-2);
                        timerElement.text('남은 시간 ' + formattedTime);
                        timerElement.css({
                            "color" : "#6667AB",
                            "font-weight" : "bold",
                            "font-size" : "10px",
                            "text-align" : "end"
                        })

                        if (timeLimit <= 0) {
                            clearInterval(timer);
                            inputElement.prop('disabled', true);
                            timerElement.text('시간 종료');
                            timerElement.css({
                                "color" : "#FA3E3E",
                                "font-weight" : "bold",
                                "font-size" : "10px",
                                "text-align" : "end"
                            })
                        }
                        timeLimit--; // 1초 감소
                    }, 1000);

                    alert("해당 이메일로 인증번호 발송이 완료되었습니다. \n 확인해주세요.");

                    chkEmailConfirm(data,$("#memailconfirm"),$("#memailconfirmTxt"));
                })
                .catch(error => {
                    // 오류가 발생했을 때 처리한다.
                    console.error("Error:", error);
                });
        }
    })

    function chkEmailConfirm(data, $memailconfirm, $memailconfirmTxt){
        $memailconfirmTxt.click(function(){
            if (data !== $memailconfirm.val()) {
                alert("인증번호가 잘못되었습니다.");
            } else {
                fetch("/auth/request-checkValid-mail", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(email)
                })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error("Network response was not ok");
                        }
                        var logo =
                            "<div style=\"text-align: center\">"
                            + "<img src=\"/images/common/icon_group_check_line.svg\" alt=\"checkLine\">"
                            + "</div>"
                        var title =
                            "<div>"
                            + "<p class='box_tit'>아이디 전송 완료</p>"
                            + "</div>"
                        var afterChkCode =
                            "<p>아이디를 <span><b>" + email + "</b></span>(으)로 발송하였습니다.</p><br />"
                            + "<p>이메일을 확인하신 후 로그인해주세요!</p><br />"
                            + "<button class=\"login_btn\" type=\"button\" onclick=\"location.href='/main'\">메인으로 이동</button>"

                        $("#logo").html(logo);
                        $("#title").html(title);
                        $("#email_div").html(afterChkCode);
                    })
                    .catch(error => {
                        // 오류가 발생했을 때 처리한다.
                        console.error("Error:", error);
                    });
            }
        })
    }

    $("#memberId").change(function (){

        var id = $("#memberId").val()

        if(id.length === 0) {
            forgetPwdConfirm.idconfirmchk = false;
        } else {
            forgetPwdConfirm.idconfirmchk = true;
        }
    });

    $("#memail").change(function (){
        var email = $("#memail").val()

        if(email.length === 0) {
            forgetPwdConfirm.emconfirmchk = false;
        } else {
            forgetPwdConfirm.emconfirmchk = true;
        }
    })

    $("#tempPwd").click(function (){
        if(!forgetPwdConfirm.idconfirmchk) {
            alert("아이디를 입력해주세요.");
            $("#memberId").focus();
        } else if(!forgetPwdConfirm.emconfirmchk){
            alert("이메일을 입력해주세요.")
            $("#memail").focus();
        } else {
            var memberId = $("#memberId").val(); // 아이디 값 가져오기
            var email = $("#memail").val(); // 이메일 값 가져오기
            console.log(memberId);
            console.log(email)
            var data = {
                memberId: memberId,
                email: email
            };
            console.log(data)

            fetch("/auth/request-checkValid-id-and-email",{
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            })
                .then(response =>{
                    if(!response.ok) {
                        alert("유효한 요청이 아닙니다.\n입력한 내용을 다시 한번 확인해주세요.");
                        throw new Error("Network response was not ok");
                    }
                    return response.json();
                })
                .then(data => {
                    if( data === 0 ) {
                        return alert("해당하는 유저 정보가 없습니다.")
                    } else {
                        var logo =
                            "<div style=\"text-align: center\">"
                            + "<img src=\"/images/common/icon_group_check_line.svg\" alt=\"checkLine\">"
                            + "</div>"
                        var title =
                            "<div>"
                            + "<p class='box_tit'>임시 비밀번호 전송 완료</p>"
                            + "</div>"
                        var afterChkCode =
                            "<p>임시 비밀번호를 <span><b>" + email + "</b></span>(으)로 발송하였습니다.</p><br />"
                            + "<p>이메일을 확인하신 후 로그인해주세요!</p><br />"
                            + "<button class=\"login_btn\" type=\"button\" onclick=\"location.href='/main'\">메인으로 이동</button>"

                        $("#logo").html(logo);
                        $("#title").html(title);
                        $("#email_div").html(afterChkCode);
                    }
                })

        }
    })

});

