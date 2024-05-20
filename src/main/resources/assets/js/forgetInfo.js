var forgetPwdConfirm = {
    idconfirmchk : false,
    emconfirmchk : false
}

$(document).ready(function () {

    $(".confirm_btn").click((e) => { // 확인버튼 클릭시
        $(".pop_bg, .pop_cont").removeClass("active"); // 팝업창/팝업배경 비활성화
        $("html, body").removeClass("fixed");
    })

    const no_email = () => {
        $(".pop_cont").find("p").text("존재하는 이메일이 없습니다.\n이메일을 다시 한 번 확인해주세요.");
        $(".pop_bg, .pop_cont").addClass("active");
        $("html, body").addClass("fixed");
    }

    const no_user = () => {
        $(".pop_cont").find("p").text("해당하는 유저 정보가 없습니다.");
        $(".pop_bg, .pop_cont").addClass("active");
        $("html, body").addClass("fixed");
    }

    var email;

    $("#checkEmail").click(function() {

        email = $("#memail").val();

        // 이메일 값을 가져온다.
        if( email === '') {
            //alert("이메일을 입력해주세요.");
            $(".pop_cont").find("p").text("이메일을 입력해주세요.");
            $(".pop_bg, .pop_cont").addClass("active");
            $("html, body").addClass("fixed");
            $("#memail").focus();
        } else {
            // Fetch를 이용하여 요청을 보낸다.
            fetch("/request-forget-verify-mail", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(email)
            })
                .then(response => {
                    if (!response.ok) {
                        alert("유효한 이메일이 아닙니다.\n입력한 이메일을 확인해주세요.");
                        // $(".pop_cont").find("p").text("유효한 이메일이 아닙니다.\n입력한 이메일을 확인해주세요.");
                        // $(".pop_bg, .pop_cont").addClass("active");
                        // $("html, body").addClass("fixed");

                        throw new Error("Network response was not ok");
                    }
                    return response.json();
                })
                .then(data => {
                    if(data === null) {
                        //return alert("존재하는 이메일이 없습니다.\n이메일을 다시 한 번 확인해주세요.")
                        return no_email();
                    }

                    var afterChkEmail =
                        "<div>"
                        +   "<h4 style=\"font-weight:var(--regular); font-size: 14px; color: #6667AB\">"
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

                    var timeLimit = 60; // 제한 시간 (초)
                    var timerElement = $('#timer');
                    var inputElement = $('#memailconfirm');

                    var timer = setInterval(function() {
                        var minutes = Math.floor(timeLimit / 60);
                        var seconds = timeLimit % 60;

                        // 시간을 "mm:ss" 형식으로 표시
                        var formattedTime = ('0' + minutes).slice(-2) + ':' + ('0' + seconds).slice(-2);
                        timerElement.text(formattedTime);
                        // timerElement.text('남은 시간 ' + formattedTime);
                        timerElement.css({
                            "color" : "#6667AB",
                            "font-weight" : "bold",
                            "font-size" : "14px",
                            "text-align" : "end"
                        })

                        if (timeLimit <= 0) {
                            clearInterval(timer);
                            inputElement.prop('disabled', true);
                            timerElement.text('시간 초과');
                            timerElement.css({
                                "color" : "#FA3E3E",
                                "font-weight" : "bold",
                                "font-size" : "14px",
                                "text-align" : "end"
                            })
                            $(".pop_cont").find("p").text("인증시간을 초과했습니다.\n이메일 인증을 다시 해주세요.");
                            $(".pop_bg, .pop_cont").addClass("active");
                            $("html, body").addClass("fixed");
                        }
                        timeLimit--; // 1초 감소
                    }, 1000);

                    // alert("해당 이메일로 인증번호 발송이 완료되었습니다. \n 확인해주세요.");
                    $(".pop_cont").find("p").text("해당 이메일로 인증번호 발송이 완료되었습니다.\n확인해주세요.");
                    $(".pop_bg, .pop_cont").addClass("active");
                    $("html, body").addClass("fixed");

                    chkEmailConfirm(data,$("#memailconfirm"),$("#memailconfirmTxt"));
                })
                .catch(error => {
                    // 오류가 발생했을 때 처리한다.
                    console.error("Error:", error);
                });
        }
    })

    function chkEmailConfirm(data, $memailconfirm, $memailconfirmTxt){
                    var timerElement = $('#timer');
        $memailconfirmTxt.click(function(){
            if (data !== $memailconfirm.val()) {
                if($memailconfirm.val() == null || $memailconfirm.val() ==''){
                    if(timerElement.text() == '시간 초과'){
                        $(".pop_cont").find("p").text("인증시간을 초과했습니다.\n이메일 인증을 다시 해주세요.");
                    }
                    //alert("인증번호가 잘못되었습니다.");
                    $(".pop_cont").find("p").text("인증번호가 미입력상태입니다.\n인증번호를 입력해주세요.");
                }else{
                    $(".pop_cont").find("p").text("인증번호가 잘못되었습니다.");
                }
                $(".pop_bg, .pop_cont").addClass("active");
                $("html, body").addClass("fixed");

            } else {
                fetch("/request-checkValid-mail", {
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
            //alert("아이디를 입력해주세요.");
            $(".pop_cont").find("p").text("아이디를 입력해주세요.");
            $(".pop_bg, .pop_cont").addClass("active");
            $("html, body").addClass("fixed");
            $("#memberId").focus();
        } else if(!forgetPwdConfirm.emconfirmchk){
            //alert("이메일을 입력해주세요.")
            $(".pop_cont").find("p").text("이메일을 입력해주세요.");
            $(".pop_bg, .pop_cont").addClass("active");
            $("html, body").addClass("fixed");
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

            fetch("/request-checkValid-id-and-email",{
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            })
                .then(response =>{
                    if(!response.ok) {
                        //alert("유효한 요청이 아닙니다.\n입력한 내용을 다시 한번 확인해주세요.");
                        $(".pop_cont").find("p").text("유효한 요청이 아닙니다.\n입력한 내용을 다시 한번 확인해주세요.");
                        $(".pop_bg, .pop_cont").addClass("active");
                        $("html, body").addClass("fixed");
                        throw new Error("Network response was not ok");
                    }
                    return response.json();
                })
                .then(data => {
                    if( data === 0 ) {
                        //return alert("해당하는 유저 정보가 없습니다.")
                        return no_user();
                    } else {
                        var logo =
                            "<div style=\"text-align: center\">"
                            + "<img src=\"/images/common/icon_group_check_line.svg\" alt=\"checkLine\">"
                            + "</div>"
                        var title =
                            "<div>"
                            + "<p class='box_tit' style='text-align: center;'>임시 비밀번호 전송 완료</p>"
                            + "</div>"
                        var afterChkCode =
                            "<p style='text-align: center;'>임시 비밀번호를 <span><b><br>" + email + "</b></span>(으)로<br>발송하였습니다.</p><br />"
                            + "<p style='text-align: center;'" +
                            ">이메일을 확인하신 후 로그인해주세요!</p><br />"
                            + "<button class=\"login_btn\" type=\"button\" onclick=\"location.href='/main'\">메인으로 이동</button>"

                        $("#logo").html(logo);
                        $("#title").html(title);
                        $("#email_div").html(afterChkCode);
                    }
                })

        }
    })

});

