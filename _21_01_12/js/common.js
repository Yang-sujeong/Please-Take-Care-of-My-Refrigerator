
// 변수 선언
var listCount = null;
var allList = [];
var activeList = [];
var completedList = [];
var onlyMenu = false;

//  ready
$(document).ready(function () {
    // listCount();
    addList();
    deleteList();
    moveList()
    updateList();
    // 목록
    viewAll();
    viewActive()
    viewCompleted();
});

// menu_All
function viewAll() {
    var $liList = $("ul.sub_menu li")
    var $li_1 = $liList.eq(0);

    $($li_1).on("click", function () {
        // 모바일, 테블릿 메뉴탭 화면 상태 => onlyMenu == true
        if (onlyMenu == true) {
            $("#sidebar").css("display", "none");
            $("#content").css("display", "block");
        }
        
        $("#contentWrap2").css("display", "block");
        $("#contentWrap3").css("display", "block");
        $("#contentWrap3").css("top", "50%");

    });

}
// menu_Active
function viewActive() {
    var $liList = $("ul.sub_menu li")
    var $li_2 = $liList.eq(1);
    $($li_2).on("click", function () {

        if (onlyMenu == true) {
            $("#sidebar").css("display", "none");
            $("#content").css("display", "block");
        }

        $("#contentWrap1").css("display", "none");
        $("#contentWrap3").css("display", "none");
        $("#contentWrap2").css("display", "block");


        var Count = document.getElementById('contentWrap2 ');
        var actCount = Count.childElementCount;
        console.log(actCount);
    });

}
// menu_Completed
function viewCompleted() {
    var $liList = $("ul.sub_menu li")
    var $li_3 = $liList.eq(2);
    $($li_3).on("click", function () {
        if (onlyMenu == true) {
            $("#sidebar").css("display", "none");
            $("#content").css("display", "block");
        }

        $("#contentWrap1").css("display", "none");
        $("#contentWrap2").css("display", "none");
        $("#contentWrap3").css("display", "block");
        $("#contentWrap3").css("top", "60px");

    });

}

// 목록보기 아이콘 클릭시 사이드바 block
function openGnb() {
    $(document).on("click", ".only_m", function () {
        onlyMenu = true;

        $("#sidebar").css("display", "block");
        $("#content").css("display", "none");

    });
}

// 목록 추가
function addList() {
    $("#contentWrap2 button.addbtn").on("click", function () {
        var input_data = window.prompt("내용을 입렵해주세요");
        $("#contentWrap2 ul.list").append(`<li><button type="submit" class="checkbtn"> 체크 </button> <p> ${input_data} </p> </li>`);

    });

}
// 수정하기
function updateList() {
    // list 의 글자 클릭시 
    $(document).on("click","#contentWrap2 .list li p", function () {
        var update = window.prompt("변경된 내용을 입력해주세요");
        $(this).text(update);

    });
}

// Active에서 체크박스 클릭시 Active 목록에서 삭제, Completed에서 확인가능(이동)
function moveList() {
    $(document).on('click', "#contentWrap2 .list li .checkbtn", function () {
        var clickList = $(this).parents("li").html();
        // console.log(clickList);
        $("#contentWrap3 ul.list").append(`<li> ${clickList}</li>`);
        var idx = $("#contentWrap2 .list li .checkbtn").index(this);
        $("#contentWrap2 .list li").eq(idx).remove();
        
    });


}

// Completed 목록에서 체크박스 클릭시 전부 삭제
function deleteList() {
    $(document).on('click', "#contentWrap3 .list li .checkbtn", function () {
        var idx = $("#contentWrap3 .list li .checkbtn").index(this);
        $("#contentWrap3 .list li").eq(idx).remove();

    });

}

//값 유효성 , 어떤 값이 자료형에 상관없이 빈 값인지 알고 싶을때
function noValue(value) {
    if (value == "" || value == null || value == undefined || (value != null && typeof value == "object" && !Object.key(value).length)) {
        return true;
        esle
        return false;
    }
}


