var forgetIdConfirm = {
    emconfirmchk : false
}

var forgetPwdConfirm = {
    idconfirmchk : false,
    emconfirmchk : false
}

$(document).ready(function () {

    $("#checkEmail").click(function() {

        // 제한시간을 설정한다.
        var timeLimit = 300; // 제한 시간 (초)
        var timerElement = $('#timer');
        var inputElement = $('#limitedInput');

        var timer = setInterval(function() {
            var minutes = Math.floor(timeLimit / 60);
            var seconds = timeLimit % 60;

            // 시간을 "mm:ss" 형식으로 표시
            var formattedTime = ('0' + minutes).slice(-2) + ':' + ('0' + seconds).slice(-2);
            timerElement.text('남은 시간: ' + formattedTime);

            if (timeLimit <= 0) {
                clearInterval(timer);
                inputElement.prop('disabled', true);
                timerElement.text('시간 종료');
            }
            timeLimit--; // 1초 감소
        }, 1000);

        // 이메일 값을 가져온다.
        var email = $("#memail").val();
        if( email === '') {
            alert("이메일을 입력해주세요.");
            $("#memail").focus();
        } else {
            // Fetch를 이용하여 요청을 보낸다.
            fetch("/request-verify-mail", {
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
                    // 성공적인 응답을 처리한다.
                    alert("해당 이메일로 인증번호 발송이 완료되었습니다. \n 확인해주세요.");

                    var afterChkEmail =
                        "<div>"
                        + "<h5 style=\"font-weight:bold; font-size: 10px; color: #6667AB\">"
                        + "이메일로 전송 받은 인증 코드를 입력해주세요.<br>"
                        + "제한 시간 초과 시 재전송 받아야해요."
                        + "</h5>"
                        + "</div>"
                        + "<input class=\"input_item\" type=\"text\" id=\"memailconfirm\" placeholder=\"인증번호를 입력해주세요\">"
                        + "<span class=\"show_pw_btn\"><img th:src=\"@{/images/common/icons_show.png}\" alt=\"popup icon image\"></span>"
                        + "<div class=\"message_box\">"
                        + "</div>"
                        + "<div style=\"padding-left: 20px; display: inline-block\">"
                        + "<button className=\"btn_item\" type=\"button\" id=\"checkEmail\">인증하기</button>"
                        + "</div>";

                    $("#email_div").html(afterChkEmail);
                    chkEmailConfirm(data, $("#memailconfirm"), $("#memailconfirmTxt"));
                })
                .catch(error => {
                    // 오류가 발생했을 때 처리한다.
                    console.error("Error:", error);
                });
        }
    })

    function chkEmailConfirm(data, $memailconfirm, $memailconfirmTxt){
        $memailconfirm.on("click", function(){
            if (data != $memailconfirm.val()) {
                alert("인증번호가 잘못되었습니다.")
                $("#memailconfirmTxt").css({
                    "color" : "#FA3E3E",
                    "font-weight" : "bold",
                    "font-size" : "10px"
                })
            } else {
                forgetIdConfirm.emconfirmchk = true;
                $("#memailconfirmTxt").css({
                    "color" : "#6667AB",
                    "font-weight" : "bold",
                    "font-size" : "10px"
                });
            }
        })
    }

});
