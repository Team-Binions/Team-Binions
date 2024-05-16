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