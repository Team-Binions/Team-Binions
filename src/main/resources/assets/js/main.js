// 상단 배너 슬라이드
const mainSwiper = new Swiper('.mainSwiper', {
    // Optional parameters
    direction: 'horizontal',
    loop: true,
    autoplay: {
        delay: 5000,
    },
    pagination: {
        el: '.swiper-pagination',
    },
});

// 예리뷰 슬라이드
const reviewSwiper = new Swiper('.reviewSwiper', {
    // Optional parameters
    slidesPerView: 3,
    spaceBetween: 20,
    direction: 'horizontal',
    loop: true,

    // Navigation arrows
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },
});

$("#inquiry").click(function (){
    if($("#inquiryEmail").val().length === 0){
        alert("이메일을 입력해 주세요.");
        $('#inquiryEmail').focus();
    } else if($("#inquiryContext").val().length === 0){
        alert("내용을 입력해 주세요.");
        $("#inquiryContext").focus();
    } else {

        const inquiryEmail = $("#inquiryEmail").val();
        const inquiryContext = $("#inquiryContext").val();

        const data = {
            email : inquiryEmail,
            context : inquiryContext
        }
        console.log(data);

        fetch("/inquiry", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Network response was not ok");
                }
                alert("관리자에게 문의를 보냈습니다!\n문의 답변은 이메일로 확인해주세요.");
                return window.location.href="/main";
            })
            .catch(error => {
                // 오류가 발생했을 때 처리한다.
                console.error("Error:", error);
            });
    }


})

