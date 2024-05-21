

$(document).ready(function () {
    const mainCategorySelect = $('#mainCategory');
    const subCategorySelect = $('#subCategory');

    subCategorySelect.prop('disabled', true);

    mainCategorySelect.change(function () {

        subCategorySelect.empty();

        if (mainCategorySelect.val() === "") {
            subCategorySelect.prop('disabled', true);
        } else {
            subCategorySelect.prop('disabled', false)
        }


        switch (mainCategorySelect.val()) {
            case '자유':
                addSubCategoryOptions(['예신', '예랑']);
                break;
            case '리뷰':
                addSubCategoryOptions(['웨딩', '스드메', '기타']);
                break;
            default :
                addSubCategoryOptions(['']);
                break;
        }
    })

    function addSubCategoryOptions(options) {
        $.each(options, function (index, option) {
            subCategorySelect.append($('<option>', {
                value: option,
                text: option
            }));
        });
    }

    //팝업창

    $(".confirm_btn").click(() => {
        $(".pop_bg, .pop_cont").removeClass("active"); // 팝업창/팝업배경 비활성화
        $("html, body").removeClass("fixed");
        $('.schedule_tit').focus();
    })

    //스케쥴 제목 입력시 텍스트 길이 실시간 반영
    $(".schedule_tit").on('input', (e) => {
        $(".tit_counted").text(e.target.value.length);
    })

    //스케쥴 내용 입력시 텍스트 길이 실시간 반영
    $(".schedule_contxt_box").on('input', (e) => {
        $(".txt_counted").text(e.target.value.length);
    })

    // 파일 선택 이벤트
    $('input[name=uploadFiles]').on('change', function (e) {
        var files = e.target.files;
        var filesArr = Array.prototype.slice.call(files);
        const current_preview_img = $('.preview_list .preview_img').length; // 파일 열기 활성화시 미리보기 갯수,

        // 업로드 된 파일 유효성 체크
        if (filesArr.length > 5 || filesArr.length > (5 - current_preview_img) ) {
            //<span class="pop_icon"><img th:src="@{/images/common/icon_group_check_line.svg}" alt="popup icon image"></span>
            $(".pop_cont").find("p").text("이미지는 최대 5개까지 업로드 가능합니다.");
            $(".pop_icon").find("img").attr("src", "/images/common/icon_group_modal_bang.svg");
            $(".pop_bg, .pop_cont").addClass("active");
            $("html, body").addClass("fixed");
            // $('input[name=uploadFiles]').val();
            return;
        }

    });


    // 파일 업로드 시 미리보기 추가
    const previewTag = (imgSrc) => `<li class="preview_img">
                  <img src="${imgSrc}" alt="preview image">
                  <span class="remove_preview_btn">×</span>
                </li>`;

    // 선택 파일 삭제
    $(document).on('click', ".remove_preview_btn", (e) => {
        const input = $('input[name=uploadFiles]');
        const files = input[0].files;
        const index = $(e.target).closest("li").index();
        //array.splice(deletedIndex, 1); // 삭제할 인덱스, 1 개만 삭제 (즉, 3만 삭제)
        imgTemp.splice(index,1);

        const dataTransfer = new DataTransfer();
        let fileArray = Array.from(files)
        fileArray.splice(index, 1);
        fileArray.forEach(file => {
            dataTransfer.items.add(file);
        });//남은 배열을 dataTransfer로 처리(Array -> FileList)
        input[0].files = dataTransfer.files;	//제거 처리된 FileList를 돌려줌
        if ($('.preview_list .preview_img').length == 5) { //마지막 미리보기 이미지 삭제시
            $(".upload_img_btn").removeClass("inactive"); // 업로드 버튼 활성화
        }
        $(e.target).closest("li").remove();
        $(".count_preview").text($('.preview_list .preview_img').length);
    })


    // 업로드 파일 경로 읽어오기
    function readURL(input, index) {
        if (input.files && input.files[0]) {
            const files = input.files;
            const current_preview_img = $('.preview_list .preview_img').length; // 파일 열기 활성화시,
            const countLeft = 5 - current_preview_img; // 파일 열기 시점에서 미리보기 갯수를 제외한 업로드 가능 갯수
            if(countLeft == 0){ // 남은 업로드 갯수가 없다면
                return false;
            }else { // 남은 업로드 갯수가 있고
                if(countLeft < files.length){//파일 열기한 파일 갯수가 남은 업로드 갯수보다 높다면
                    $(".pop_cont").find("p").text("이미지는 최대 5개까지 업로드 가능합니다.");
                    $(".pop_bg, .pop_cont").addClass("active");
                    $("html, body").addClass("fixed");
                }

                if(files.length == 1){
                    const reader = new FileReader();
                    reader.readAsDataURL(files[0]);
                    //console.log('files : ' + );
                    reader.onload = function (e) {
                        $('.preview_list').prepend(previewTag(e.target.result));
                        $(".count_preview").text($('.preview_list .preview_img').length);
                        const current_preview_img = $('.preview_list .preview_img').length;
                        if(current_preview_img == 5){
                            $(".upload_img_btn").addClass("inactive"); // 업로드 버튼 비활성화
                        }
                        // if (i === 4 || current_preview_img) {
                        //     $(".upload_img_btn").addClass("inactive"); // 업로드 버튼 비활성화
                        // }
                    }
                }else{
                    for (let i = 0; i < countLeft; i++) { // 남은 갯수만큼 업로드
                        const reader = new FileReader();
                        reader.readAsDataURL(files[i]);

                        reader.onload = function (e) {
                            $('.preview_list').prepend(previewTag(e.target.result));
                            $(".count_preview").text($('.preview_list .preview_img').length);
                            const current_preview_img = $('.preview_list .preview_img').length;
                            if(current_preview_img == 5){
                                $(".upload_img_btn").addClass("inactive"); // 업로드 버튼 비활성화
                            }
                            // if (i === 4 || current_preview_img) {
                            //     $(".upload_img_btn").addClass("inactive"); // 업로드 버튼 비활성화
                            // }
                        }

                    }
                }
            }


        }
    }

    let imgTemp = [];
    $(".file_upload_input").change(function () {
        const index = $(".file_upload_input").index(this); // index를 올바르게 가져오기 위해 'this' 사용
        console.log('파일업로드시 index : ' + index);
        const preview_count = $(".preview_list .preview_img").length; //파일열기한 시점의 미리보기 개수
        const countLeft = 5 - preview_count; // 업로드 가능한 파일 수 (5 - 미리보기 개수)
        readURL(this, index);

        // 선택된 파일들을 가져온다
        const files = this.files;

        // formData 객체에 모든 파일을 추가
        var formData = new FormData();
        if(countLeft > files.length){ // 남은 가능한 업로드 갯수가 파일열기한 갯수보다 크다면
            if(files.length == 1){
                console.log("files.length : " + files.length);
            }
            for(let i=0; i < files.length; i++){ //남은 업로드 가능한 파일갯수만큼
                formData.append("file", files[i]);
            }
        }else{ // 남은 가능한 업로드 갯수가 파일열기한 갯수보다 작다면
            for(let i=0; i < countLeft; i++){ //남은 업로드 가능한 파일갯수만큼
                formData.append("file", files[i]);
            }

        }

        // Array.from(files).forEach((file) => {
        //     formData.append("file", file);
        // });
        console.log(formData);

        // Fetch를 이용하여 요청을 보낸다.
        fetch("/user/uploadAjax", {
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

                if(data == null) {
                    return alert("실패");
                }
                console.log("현재 imgTemp에 담을 data length : " + data.length);
                // 서버에서 반환한 데이터가 여러 파일에 대한 것일 경우 처리
                Array.from(data).forEach((fileData) => {
                    const imgTmp = {};
                    imgTmp["fileName"] = fileData; // "fileName"을 키로 사용하여 data를 객체에 추가
                    imgTemp.push(imgTmp); // imgTmp 객체를 imgTemp 배열에 추가
                });

                console.log("imgTemp:", imgTemp);
            })
            .catch(error => {
                // 오류가 발생했을 때 처리한다.
                console.error("Error:", error);
            });

    });

    $("#registPost").click(function () {
        const memberCodeString = $("#memberCode").val();
        const memberCode = parseInt(memberCodeString, 10);
        if (isNaN(memberCode)) {
            console.error("memberCode is not a valid integer.");
            return; // 유효하지 않은 경우 작업 중단
        }
        const postTitle = $("#postTitle").val();
        const postContext = $("#postContext").val();
        const mainCategory = $("#mainCategory").val();
        const subCategory = $("#subCategory").val();

        const postInfo = {
            memberCode: memberCode,
            postTitle: postTitle,
            postContext: postContext,
            mainCategory: mainCategory,
            subCategory: subCategory
        };

        fetch("/user/registPost", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(postInfo)
        })
            .then(res => {
                if (!res.ok) {
                    throw new Error("Network response was not ok");
                }
                return res.json();
            })
            .then(data => {
                if (data == null) {
                    $(".pop_cont").find("p").text("게시글을 등록하지 못했습니다.\n관리자에게 문의해주세요.");
                    $(".pop_icon").find("img").attr("src", "/images/common/icon_group_modal_bang.svg");
                    $(".pop_bg, .pop_cont").addClass("active");
                    $("html, body").addClass("fixed");
                    return window.location.href = "/";
                }

                // imgTemp 배열의 각 객체에 postCode 추가
                imgTemp = imgTemp.map(img => {
                    return {
                        ...img,
                        postCode: data.postCode
                    };
                });

                console.log("imgTemp:", imgTemp);

                return fetch("/user/registerFiles", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(imgTemp)
                });
            })
            .then(res => {
                if (!res.ok) {
                    throw new Error("Network response was not ok");
                }
                //alert("게시글 등록 성공!")
                //return window.location.href="/main";
                return popUp();
            })
            .catch(error => {
                $(".pop_cont").find("p").text("제목 또는 내용입력을 확인해주세요.");
                $(".pop_icon").find("img").attr("src", "/images/common/icon_group_modal_bang.svg");
                $(".pop_bg, .pop_cont").addClass("active");
                $("html, body").addClass("fixed");
                return console.error("Error:", error);
            });
    });

    function popUp(){
        $(".pop_cont").find("p").text("게시글이 등록되었습니다.");
        $(".pop_icon").find("img").attr("src", "/images/common/icon_group_modal_check.svg");
        $(".pop_bg, .pop_cont").addClass("active");
        $("html, body").addClass("fixed");
        $(".confirm_btn").on("click", () => {
            window.location.href="/main";
        })
    }
})