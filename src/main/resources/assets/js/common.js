//header sub menu 활성화
const gnbMenu = document.querySelectorAll('.header_gnb > li > a');
const subMenu = document.querySelectorAll('.sub_menu');
gnbMenu.forEach(gnbmenu => {
    if(gnbmenu.textContent == "예리뷰"){
        gnbmenu.addEventListener('mouseover', (e) => {
            e.currentTarget.classList.add("active");
        })
        gnbmenu.addEventListener('mouseout', (e) => {
            e.currentTarget.classList.remove("active");
        })
    }else{
        gnbmenu.addEventListener('mouseover', (e) => {
            e.currentTarget.classList.add("active");
            e.currentTarget.nextElementSibling.classList.add("active");
        })
        gnbmenu.addEventListener('mouseout', (e) => {
            e.currentTarget.classList.remove("active");
            e.currentTarget.nextElementSibling.classList.remove("active");
        })
    }

});

subMenu.forEach(submenu => {
    submenu.addEventListener('mouseover', (e) => {
        //console.log(e.currentTarget.previousElementSibling);
        e.currentTarget.previousElementSibling.classList.add("active");
        e.currentTarget.classList.add("active");
    })
    submenu.addEventListener('mouseout', (e) => {
        e.currentTarget.previousElementSibling.classList.remove("active");
        e.currentTarget.classList.remove("active");
    })
})

// TOP BUTTON
// 윈도우 스크롤 최상단으로 이동
const topBtn = document.querySelector('.top_btn');

const scrollTop = () => {
    window.scrollTo({
        top : 0,
        behavior : 'smooth'
    });
    topBtn.classList.remove('active');
}

if(topBtn){

    topBtn.addEventListener('click', () => {
        scrollTop();
    })
}

window.addEventListener('scroll', () => {
    console.log(window.scrollY);
    if(window.scrollY > 500){ //스크롤 위치 y값이 500 초과이면
        if(topBtn){
            topBtn.classList.add('active'); // top 버튼 활성화
        }
    } else { // 스크롤 위치 y값이 500 미만이면
        if(topBtn){
            topBtn.classList.remove('active'); // top 버튼 비활성화
        }
    }
})