
const totalConfirm = {
    trmconfirmchk : false,
    idconfirmchk : false,
    pwdconfirmchk : false,
    nknconfirmchk : false,
    emchk : false,
    emconfirmchk : false,
    gdconfirmchk : false,
};

$(document).ready(function () {
    $("#agree").click(function(){
        if($("#agree").is(':checked')){
            totalConfirm.trmconfirmchk = true;
        } else {
            totalConfirm.trmconfirmchk = false;
        }
    })

    $('#memberId').keyup(function (){

        var id = $(event.target).val();
        var num = id.search(/[0-9]/g);
        var eng = id.search(/[a-z]/ig);
        var pattern = /\s/;

        if ( id.length < 5 || id.length > 15) {
            totalConfirm.idconfirmchk = false;
            $('#idTxt').html("<span id='idconfirmchk'>*아이디는 최대 5~15자 까지 가능합니다</span>")
            $("#idconfirmchk").css({
                "padding-left" : "145px",
                "font-weight" : "bold",
                "font-size" : "10px"
            })
        } else if (pattern.test(id)) {
            totalConfirm.idconfirmchk = false;
            $('#idTxt').html("<span id='idconfirmchk'>! 아이디에 공백이 포함될 수 없습니다</span>")
            $("#idconfirmchk").css({
                "color" : "#FA3E3E",
                "padding-left" : "145px",
                "font-weight" : "bold",
                "font-size" : "10px"
            })
        } else if ( (id.length != 0) && (num < 0 || eng < 0) ) {
            totalConfirm.idconfirmchk = false;
            $('#idTxt').html("<span id='idconfirmchk'>! 아이디는 영어+숫자로 이루어져야 합니다</span>")
            $("#idconfirmchk").css({
                "color" : "#FA3E3E",
                "padding-left" : "145px",
                "font-weight" : "bold",
                "font-size" : "10px"
            })
        } else {
            $('#idTxt').html("<span id='idconfirmchk'></span>")
        }
    })

    $("#checkId").click(function() {
        // 아이디 값을 가져온다.
        var id = $("#memberId").val();
        var num = id.search(/[0-9]/g);
        var eng = id.search(/[a-z]/ig);
        var pattern = /\s/;

        if( id === '' ) {
            alert("아이디를 입력해주세요.");
            $("#memberId").focus();
        } else if ( id.length < 5 || id.length > 15 ) {}
        else if (pattern.test(id)) {}
        else if ( num < 0 || eng < 0 ) {}
        else {
            // Fetch를 이용하여 요청을 보낸다.
            fetch("/request-dupCheck-id", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(id)
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Network response was not ok");
                    }
                    return response.json();
                })
                .then(data => {
                    // 성공적인 응답을 처리한다.
                    // console.log(data);
                    chkDupIdConfirm(data, $("#idTxt"));
                })
                .catch(error => {
                    // 오류가 발생했을 때 처리한다.
                    console.error("Error:", error);
                });
        }
    });


    $("#password").keyup(function () {
        var value = $(event.target).val();

        var num = value.search(/[0-9]/g);
        var eng = value.search(/[a-z]/ig);
        var spe = value.search(/[`~!@#$%^&*|\\\'\";:\/?]/gi);
        var pattern = /\s/;

        if(value.length < 8 || value.length > 30) {
            $("#pwdTxt").css({
                "color" : "#FA3E3E",
                "font-weight" : "bold",
                "font-size" : "10px"
            });
            $("#pwdTxt").text("! 비밀번호는 8자리 이상 30자리 이하여야 합니다");
            totalConfirm.pwdconfirmchk = false;
        } else if (num < 0 || eng < 0 || spe < 0){
            $("#pwdTxt").css({
                "color" : "#FA3E3E",
                "font-weight" : "bold",
                "font-size" : "10px"
            });
            $("#pwdTxt").text("! 비밀번호는 영어+숫자+특수문자로 이루어져야 합니다");
            totalConfirm.pwdconfirmchk = false;
        } else if ( pattern.test(value) ){
            $("#pwdTxt").css({
                "color" : "#FA3E3E",
                "font-weight" : "bold",
                "font-size" : "10px"
            });
            $("#pwdTxt").text("! 비밀번호에 공백은 사용할 수 없습니다");
            totalConfirm.pwdconfirmchk = false;
        } else {
            $("#pwdTxt").css({
                "color" : "#6667AB",
                "font-weight" : "bold",
                "font-size" : "10px"
            });
            $("#pwdTxt").text("✔️ 사용 가능한 비밀번호입니다");
            totalConfirm.pwdconfirmchk = true;
        }
    })

    $('#nickname').keyup(function (){

        var nickname = $(event.target).val();

        if ( nickname.length < 5 || nickname.length > 15) {
            totalConfirm.nknconfirmchk = false;
            $('#nicknameTxt').html("<span id='nknconfirmchk'>*닉네임은 최대 5~15자 까지 가능합니다.</span>")
            $("#nknconfirmchk").css({
                "padding-left" : "145px",
                "font-weight" : "bold",
                "font-size" : "10px"
            })
        }
    })

    $("#checkNkname").click(function() {
        // 닉네임 값을 가져온다.
        var nickname = $("#nickname").val();

        if( nickname === '' ) {
            alert("닉네임을 입력해주세요.");
            $("#nickname").focus();
        } else if ( nickname.length < 5 || nickname.length > 15 ) {}
        else {
            // Fetch를 이용하여 요청을 보낸다.
            fetch("/request-dupCheck-nickname", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(nickname)
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Network response was not ok");
                    }
                    return response.json();
                })
                .then(data => {
                    // 성공적인 응답을 처리한다.
                    // console.log(data);
                    chkDupNknConfirm(data, $("#nicknameTxt"));
                })
                .catch(error => {
                    // 오류가 발생했을 때 처리한다.
                    console.error("Error:", error);
                });
        }
    });

    $("#checkEmail").click(function() {
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
                        alert("유효한 이메일이 아닙니다.")
                        throw new Error("Network response was not ok");
                    }
                    return response.json();
                })
                .then(data => {
                    // 성공적인 응답을 처리한다.
                    // console.log(data);
                    // console.log(typeof data);
                    if(data > 0 & typeof data === "number") {
                        return alert("이미 존재하는 이메일입니다.");
                    }
                    alert("해당 이메일로 인증번호 발송이 완료되었습니다. \n 확인해주세요.");
                    totalConfirm.emchk = true;
                    $('#memail').prop('disabled',true);
                    chkEmailConfirm(data, $("#memailconfirm"), $("#memailconfirmTxt"));
                })
                .catch(error => {
                    // 오류가 발생했을 때 처리한다.
                    console.error("Error:", error);
                });
        }
    })

    var weddingVerified;

    $("#verifyFile").change(function() {

        // 파일을 가져온다.
        var file = event.target.files[0];
        // console.log(file);

        // formData객체에 파일(MultipartFile)형태 그대로 담는다
        var formData = new FormData();
        formData.append("file",file);
        // console.log(formData.get("file"));

        // Fetch를 이용하여 요청을 보낸다.
        fetch("/request-verify-wedd", {
            method: "POST",
            body: formData
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Network response was not ok");
                }
                return response.json();
            })
            .then(data => {
                // 성공적인 응답을 처리한다.
                console.log(data);
                weddingVerified = data;
            })
            .catch(error => {
                // 오류가 발생했을 때 처리한다.
                console.error("Error:", error);
            });
    })

    $('#phone').keyup(function () {
        var value = $(event.target).val();
        var phone = $('#phone').val();
        var regex = new RegExp("^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$");
        if (value.length < 13 || value.length > 13 ) {
            $('#phoneTxt').html("<span id='phoneSpanTxt'>! 휴대폰번호는 하이픈 포함 13글자여야 합니다</span>")
            $('#phoneSpanTxt').css({
                "padding-left" : "145px",
                "color" : "#FA3E3E",
                "font-weight" : "bold",
                "font-size" : "10px"
            });
        } else if (!regex.test(phone)) {
            $('#phoneTxt').html("<span id='phoneSpanTxt'>! 휴대폰번호 정규식에 맞게 작성해주세요</span>")
            $('#phoneSpanTxt').css({
                "padding-left" : "145px",
                "color" : "#FA3E3E",
                "font-weight" : "bold",
                "font-size" : "10px"
            });
        } else {
            $('#phoneTxt').html("<span id='phoneSpanTxt'>✔️ 사용 가능한 번호입니다.</span>")
            $('#phoneSpanTxt').css({
                "padding-left" : "145px",
                "color" : "#6667AB",
                "font-weight" : "bold",
                "font-size" : "10px"
            });
        }
    })

    // 성별 하나만 체크 가능
    $('input[type="checkbox"][name="gender"]').click(function(){
        if($(this).prop('checked')){
            $('input[type="checkbox"][name="gender"]').prop('checked',false);
            $(this).prop('checked',true);
            totalConfirm.gdconfirmchk = true;
        } else {
            totalConfirm.gdconfirmchk = false;
        }
        // console.log(totalConfirm.gdconfirmchk);
    });

    // 결혼여부 하나만 체크 가능
    $('input[type="checkbox"][name="verifyMarried"]').click(function(){
        if($(this).prop('checked')){
            $('input[type="checkbox"][name="verifyMarried"]').prop('checked',false);
            $(this).prop('checked',true);
        }
    });

    // 회원가입 유효성 검사
    $('#join').click(function (){
        if (!totalConfirm.trmconfirmchk) {
            alert("약관 동의가 필요합니다.");
            $('html, body').animate({
                scrollTop: 0
            }, 'slow');
            $('#agree').focus();
        } else if (!totalConfirm.idconfirmchk) {
            alert("아이디를 확인해 주세요.");
            $('html, body').animate({
                scrollTop: $('#memberId').offset().top
            }, 'slow');
            $('#memberId').focus();
        } else if (!totalConfirm.pwdconfirmchk) {
            alert("비밀번호를 확인해 주세요.");
            $('html, body').animate({
                scrollTop: $('#password').offset().top
            }, 'slow');
            $('#password').focus();
        } else if (!totalConfirm.nknconfirmchk) {
            alert("닉네임을 확인해 주세요.");
            $('html, body').animate({
                scrollTop: $('#nickname').offset().top
            }, 'slow');
            $('#nickname').focus();
        } else if (!totalConfirm.emchk) {
            alert("이메일을 확인해 주세요.");
            $('html, body').animate({
                scrollTop: $('#memail').offset().top
            }, 'slow');
            $('#memail').focus();
        } else if (!totalConfirm.emconfirmchk) {
            alert("이메일 인증번호를 확인해 주세요.");
            $('html, body').animate({
                scrollTop: $('#memailconfirm').offset().top
            }, 'slow');
            $('#memailconfirm').focus();
        } else if (!totalConfirm.gdconfirmchk) {
            alert("성별을 확인해 주세요.");
            $('html, body').animate({
                scrollTop: $('#male').offset().top
            }, 'slow');
            $('#male').focus();
        } else {

            var gender = $('input[type="checkbox"][name="gender"]').val();
            var marriedStatus = $('input[type="checkbox"][name="verifyMarried"]').val();

            console.log(gender);
            console.log(marriedStatus);
            console.log(weddingVerified);

            var memberJoin = {
                "memberId" : $('#memberId').val(),
                "memberPw" : $('#password').val(),
                "nickname" : $('#nickname').val(),
                "email" : $('#memail').val(),
                "phone" : $("#phone").val(),
                "gender" : gender,
                "marriedStatus" : marriedStatus,
                "weddingVerified" : weddingVerified
            }

            fetch("/request-join-member", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(memberJoin)
            })
                .then(response => {
                    if (!response.ok) {
                        alert("회원가입이 실패하였습니다..")
                        throw new Error("Network response was not ok");
                        return window.location.href="/signup";
                    }
                    alert("회원가입이 완료되었습니다! \n 로그인창으로 이동합니다..");
                    return window.location.href="/";
                })
                .catch(error => {
                    // 오류가 발생했을 때 처리한다.
                    console.error("Error:", error);
                });
        }
    })

});

// 아이디 중복확인 함수
function chkDupIdConfirm(data, $idTxt){
    if (data != 0) {
        totalConfirm.idconfirmchk = false;
        $idTxt.html("<span id='idconfirmchk'>! 이미 사용중인 아이디입니다</span>")
        $("#idconfirmchk").css({
            "padding-left" : "145px",
            "color" : "#FA3E3E",
            "font-weight" : "bold",
            "font-size" : "10px"
        })
    } else {
        totalConfirm.idconfirmchk = true;
        $('#memberid').prop('disabled',true);
        $idTxt.html("<span id='idconfirmchk'>✔️ 사용 가능한 아이디입니다</span>")
        $("#idconfirmchk").css({
            "padding-left" : "145px",
            "color" : "#6667AB",
            "font-weight" : "bold",
            "font-size" : "10px"
        })
    }
}

// 닉네임 중복확인 함수
function chkDupNknConfirm(data, $nicknameTxt){
    if (data != 0) {
        totalConfirm.nknconfirmchk = false;
        $nicknameTxt.html("<span id='nknconfirmchk'>! 이미 사용중인 별명입니다</span>")
        $("#nknconfirmchk").css({
            "padding-left" : "145px",
            "color" : "#FA3E3E",
            "font-weight" : "bold",
            "font-size" : "10px"
        })
    } else {
        totalConfirm.nknconfirmchk = true;
        $nicknameTxt.html("<span id='nknconfirmchk'>✔️ 사용 가능한 별명입니다</span>")

        $("#nknconfirmchk").css({
            "padding-left" : "145px",
            "color" : "#6667AB",
            "font-weight" : "bold",
            "font-size" : "10px"
        })
    }
}

// 이메일 인증번호 함수
function chkEmailConfirm(data, $memailconfirm, $memailconfirmTxt){
    $memailconfirm.on("keyup", function(){
        if (data != $memailconfirm.val()) {
            totalConfirm.emconfirmchk = false;
            $memailconfirmTxt.html("<span id='emconfirmchk'>! 인증번호가 잘못되었습니다</span>")
            $("#emconfirmchk").css({
                "padding-left" : "145px",
                "color" : "#FA3E3E",
                "font-weight" : "bold",
                "font-size" : "10px"
            })
        } else {
            totalConfirm.emconfirmchk = true;
            $memailconfirmTxt.html("<span id='emconfirmchk'>✔️ 인증번호 확인 완료</span>")

            $("#emconfirmchk").css({
                "padding-left" : "145px",
                "color" : "#6667AB",
                "font-weight" : "bold",
                "font-size" : "10px"
            })
        }
    })
}