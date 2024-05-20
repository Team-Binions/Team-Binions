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

const colors = ["#6667AB", "#5046BF", "#B26EAD", "#A7A8C9", "#9B9ADD"];
const numBalls = 50;
const balls = [];
const ballList = document.querySelector(".ball_bg");

for (let i = 0; i < numBalls; i++) {
    let ball = document.createElement("li");
    ball.classList.add("ball");
    ball.style.background = colors[Math.floor(Math.random() * colors.length)];
    ball.style.left = `${Math.floor(Math.random() * 1160)}px`;
    ball.style.top = `${Math.floor(Math.random() * 400)}px`;
    ball.style.transform = `scale(${Math.random()})`;
    ball.style.width = `${Math.random()}rem`;
    ball.style.height = ball.style.width;

    balls.push(ball);
    ballList.append(ball);
}

// Keyframes
balls.forEach((el, i, ra) => {
    let to = {
        x: Math.random() * (i % 2 === 0 ? -11 : 11),
        y: Math.random() * 12
    };

    let anim = el.animate(
        [
            { transform: "translate(0, 0)" },
            { transform: `translate(${to.x}rem, ${to.y}rem)` }
        ],
        {
            duration: (Math.random() + 1) * 2000, // random duration
            direction: "alternate",
            fill: "both",
            iterations: Infinity,
            easing: "ease-in-out"
        }
    );
});

