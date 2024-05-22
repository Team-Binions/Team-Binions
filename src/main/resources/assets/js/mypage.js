

const totalConfirm = {
    pwdconfirmchk : true,
    chkpwdconfirmchk : true,
    gdconfirmchk : true
};

var password = $("#password");

// 결혼인증파일
var weddingFile;

$(document).ready(function () {

    //비밀번호 보기 아이콘 버튼 클릭시, 비밀번호 노출/비노출
    $(".show_pw_btn").click((e) => {
        if($(e.target).closest("span.show_pw_btn").hasClass("active")){ // 이미 활성화된 상태라면
            $(e.target).closest("span.show_pw_btn").removeClass("active"); // 활성화 클래스 제거
            $(e.target).attr("th:src","@{/images/common/icon_show.png}"); // show 아이콘 이미지로 변경
            $(e.target).attr("src","/images/common/icon_show.png"); // show 아이콘 이미지로 변경
            $(e.target).closest("span.show_pw_btn").siblings("input").attr("type", "password");// input 타입 password로 변경
        }else{ // 비활성화된 상태라면
            $(e.target).closest("span.show_pw_btn").addClass("active"); // 활성화 클래스 추가
            $(e.target).attr("th:src","@{/images/common/icon_hide.png}"); // hide 아이콘 이미지로 변경
            $(e.target).attr("src","/images/common/icon_hide.png"); // hide 아이콘 이미지로 변경
            $(e.target).closest("span.show_pw_btn").siblings("input").attr("type", "text"); // input 타입 text로 변경
        }
    })

//회원탈퇴 버튼 클릭시, 재확인 팝업창 활성화
    $(".leave_btn").on(('click'), () => {
        $(".pop_bg, .pop_cont.selective").addClass("active");
    })

    $(".pop_btn_list > li").click((e) => {
        if($(e.target).closest("li").hasClass("cancel_btn")){//회원탈퇴 확인 버튼을 클릭시
            console.log("회원탈퇴 확인 버튼 클릭");

        }else{//회원탈퇴 취소 버튼을 클릭시
            console.log("회원탈퇴 취소 버튼 클릭");
        }
        $(".pop_bg, .pop_cont.selective").removeClass("active"); // 팝업창/팝업배경 비활성화
    })


//비밀번호 변경 버튼 클릭
    $(document).on('click','.change_pw_btn', () => {
        $(".change_password_box").addClass("active");
        $(".change_pw_btn").addClass('inactive');
        $(".change_password_check").addClass('active');
        $("#passwordHidden").prop("disabled",true);
        totalConfirm.pwdconfirmchk = false;
        totalConfirm.chkpwdconfirmchk = false;
        password.val(null);
    })

    $("#password").keyup(function () {
        var value = $(event.target).val();

        var num = value.search(/[0-9]/g);
        var eng = value.search(/[a-z]/ig);
        var spe = value.search(/[`~!@#$%^&*|\\\'\";:\/?]/gi);
        var pattern = /\s/;

        if(value.length !== 0 && (value.length < 8 || value.length > 20) ) {
            totalConfirm.pwdconfirmchk = false;
            $("#pwdTxt").css({
                "color" : "#FA3E3E",
                "font-weight" : "bold",
                "font-size" : "10px"
            });
            $("#pwdTxt").text("! 비밀번호는 8자리 이상 20자리 이하여야 합니다");
        } else if ( value.length !== 0 && pattern.test(value) ){
            totalConfirm.pwdconfirmchk = false;
            $("#pwdTxt").css({
                "color" : "#FA3E3E",
                "font-weight" : "bold",
                "font-size" : "10px"
            });
            $("#pwdTxt").text("! 비밀번호에 공백은 사용할 수 없습니다");
        } else if (value.length !== 0 && (num < 0 || eng < 0 || spe < 0) ){
            totalConfirm.pwdconfirmchk = false;
            $("#pwdTxt").css({
                "color" : "#FA3E3E",
                "font-weight" : "bold",
                "font-size" : "10px"
            });
            $("#pwdTxt").text("! 비밀번호는 영어+숫자+특수문자로 이루어져야 합니다");
        } else if ( value.length === 0 ){
            totalConfirm.pwdconfirmchk = false;
            $("#pwdTxt").css({
                "color" : "#6667AB",
                "font-weight" : "bold",
                "font-size" : "10px"
            });
            $("#pwdTxt").text("* 영문,숫자,특수문자 조합 8자 이상 입력해 주세요");
        }
        else {
            totalConfirm.pwdconfirmchk = true;
            $("#pwdTxt").css({
                "color" : "#6667AB",
                "font-weight" : "bold",
                "font-size" : "10px"
            });
            $("#pwdTxt").text("✔️ 사용 가능한 비밀번호입니다");
        }
    })

    $("#checkPw").keyup(function () {
        var value = $(event.target).val();

        if( value.length !== 0 && value !== $("#password").val() ) {
            totalConfirm.chkpwdconfirmchk = false;
            $("#pwdCheckTxt").css({
                "color" : "#FA3E3E",
                "font-weight" : "bold",
                "font-size" : "10px"
            });
            $("#pwdCheckTxt").text("! 비밀번호를 확인해주세요.");
        } else if ( value.length === 0 ){
            totalConfirm.chkpwdconfirmchk = false;
            $("#pwdCheckTxt").css({
                "color" : "#6667AB",
                "font-weight" : "bold",
                "font-size" : "10px"
            });
            $("#pwdCheckTxt").text("* 비밀번호를 한번 더 입력해주세요");
        }
        else {
            totalConfirm.chkpwdconfirmchk = true;
            $("#pwdCheckTxt").css({
                "color" : "#6667AB",
                "font-weight" : "bold",
                "font-size" : "10px"
            });
            $("#pwdCheckTxt").text("✔️ 확인되었습니다.");
        }
    })

    $('#phone').keyup(function () {
        var value = $(event.target).val();
        var phone = $('#phone').val();
        var regex = new RegExp("^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$");
        if (value.length !== 0 && (value.length < 13 || value.length > 13) ) {
            $('#phoneTxt').text("! 휴대폰번호는 하이픈 포함 13글자여야 합니다")
            $('#phoneTxt').css({
                "color" : "#FA3E3E",
                "font-weight" : "bold",
                "font-size" : "10px"
            });
        } else if (value.length !== 0 && (!regex.test(phone))) {
            $('#phoneTxt').html("! 휴대폰번호 정규식에 맞게 작성해주세요")
            $('#phoneTxt').css({
                "color" : "#FA3E3E",
                "font-weight" : "bold",
                "font-size" : "10px"
            });
        } else if (value.length === 0) {
            $('#phoneTxt').html("")
        } else {
            $('#phoneTxt').html("✔️ 사용 가능한 번호입니다")
            $('#phoneTxt').css({
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
                weddingFile = data;
                console.log(data);
            })
            .catch(error => {
                // 오류가 발생했을 때 처리한다.
                console.error("Error:", error);
            });
    })

    // 회원가입 유효성 검사
    $('#join').click(function (){
        if (!totalConfirm.pwdconfirmchk) {
            alert("비밀번호를 확인해 주세요.");
            $('html, body').animate({
                scrollTop: 200
            }, 'slow');
            $('#password').focus();
        } else if (!totalConfirm.chkpwdconfirmchk) {
            alert("비밀번호를 확인해 주세요.");
            $('html, body').animate({
                scrollTop: 250
            }, 'slow');
            $('#checkPw').focus();
        } else if (!totalConfirm.gdconfirmchk) {
            alert("성별을 확인해 주세요.");
        } else {

            console.log(weddingFile);
            if(!weddingFile){
                weddingFile = "";
            }
            console.log(weddingFile);

            var memberJoin = {
                "memberId" : $("#memberId").val(),
                "memberPw" : $("#password").val(),
                "phone" : $("#phone").val(),
                "gender" : $('input[type="checkbox"][name="gender"]').val(),
                "marriedStatus" : $('input[type="checkbox"][name="verifyMarried"]').val(),
                "weddingFile" : weddingFile
            }

            fetch("/request-modify-member", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(memberJoin)
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Network response was not ok");
                    }
                    //alert("회원 정보 수정이 완료되었습니다!");
                    function popUp(){
                        $(".pop_bg, .leavePop").addClass("active");
                        $("html, body").addClass("fixed");
                        $(".confirm_btn").click(() => {
                            $(".pop_bg, .leavePop").removeClass("active"); // 팝업창/팝업배경 비활성화
                            $("html, body").removeClass("fixed");
                            window.location.href="/mypage/myinfo";
                        })
                    }
                    return popUp();
                })
                .catch(error => {
                    // 오류가 발생했을 때 처리한다.
                    console.error("Error:", error);
                });
        }
    })

});
