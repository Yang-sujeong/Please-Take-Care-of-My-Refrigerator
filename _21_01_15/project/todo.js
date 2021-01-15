const toDoForm = document.querySelector(".js-toDoForm"),
    toDoInput = toDoForm.querySelector("input"),
    clkEmp = document.querySelector(".empty"),
    toDoList = document.querySelector(".js-toDoList"),
    allList = document.querySelector(".All"),
    activeList = document.querySelector(".Active"),
    completeList = document.querySelector(".Completed"),
    develList = document.querySelector(".Developer"),
    todoheader = document.querySelector("#todo_header"),
    todoheader_count = document.querySelector(".count"),
    plus = document.querySelector(".Btn"),
    search = document.querySelector("#search"),    // 210114 양수정 추가
    m_gnb = document.querySelector(".m_gnb"),     // 모바일 - 사이드바 여는 버튼 
    del_btn = document.querySelector(".del_btn");

const ALL_LS = 'allToDo',
    ACTIVE_LS = 'activeToDo',
    COMPLETED_LS = 'completedToDo';

let completedToDo = [],
    activeToDo = [],
    allToDo = activeToDo.concat(completedToDo);

function saveToDos() {
    localStorage.setItem(ALL_LS, JSON.stringify(allToDo));
    localStorage.setItem(ACTIVE_LS, JSON.stringify(activeToDo));
    localStorage.setItem(COMPLETED_LS, JSON.stringify(completedToDo));
}

let h1 = document.createElement("h1");
let p = document.createElement("p");

function init() {
    h1.innerText = "All";
    todoheader.appendChild(h1);
    // todoheader.appendChild(p);
    todoheader_count.style.color = "#ff9f2b";
    showCount();
    loadTodos();
    addEmpty();
    only_m();
    todoheader_count.textContent = allToDo.length;
    allToDo.forEach(toDo => paint(toDo));
    clkEmp.addEventListener("click", show_input);
    toDoForm.addEventListener("submit", handleSubmit);
}

init();
allList.addEventListener("click", paintAll);
activeList.addEventListener("click", paintActive);
completeList.addEventListener("click", paintComplete);
develList.addEventListener("click", paintdevel);
search.addEventListener("keydown", paintSearch);
search.removeEventListener("keyup", paintAll);

function handleSubmit(event) {
    event.preventDefault();
    const currentValue = toDoInput.value;
    paintToDo(currentValue);
    toDoInput.value = "";
    toDoForm.style.display = "none";
    loadTodos();
}

function loadTodos() {
    const loadedToDos = localStorage.getItem(ALL_LS);
    if (loadedToDos !== null) {
        const parsedToDos = JSON.parse(loadedToDos);

        allToDo = parsedToDos;
        completedToDo = parsedToDos.filter(toDo => toDo.isDone === true);
        activeToDo = parsedToDos.filter(toDo => toDo.isDone === false);
        showCount();
    }
}

function paintAll() {
    only_m();
    changeMenu("All", "#ff9f2b", allToDo.length);
    allToDo.forEach(toDo => paint(toDo));
    addEmpty();     // 210114 양수정 추가
}

function paintActive() {
    only_m();
    changeMenu("Active", "#0084fb", activeToDo.length);
    activeToDo.forEach(toDo => paint(toDo));
    addEmpty();     // 210114 양수정 추가
}

function paintComplete() {
    only_m();
    addEmpty();     // 210114 양수정 추가
    changeMenu("Completed", "#ecb5af", completedToDo.length);
    completedToDo.forEach(toDo => compPaint(toDo));
    del_btn.addEventListener("click", deleteToDo);
}

// 210114 양수정 추가
function paintSearch() {
    only_m();
    addEmpty();     // 210114 양수정 추가
    filter();
    changeMenu("Search", "#e9dfc8", valueCount.length);
    allToDo.forEach(toDo => paint(toDo));
}

function paintdevel() {
    only_m();
    addEmpty();     // 210114 양수정 추가
    changeMenu("Developer", "#4a9459", "4");
}

function paint(obj) {  //      목록 클릭시 해당목록 리스트 보여주기  
    const li = document.createElement("li");
    const btn = document.createElement("button");
    const span = document.createElement("span");
    span.addEventListener("click", editTodo);
    span.innerText = obj.text;
    li.appendChild(btn);
    li.appendChild(span);
    li.id = obj.id;
    toDoList.appendChild(li);

    if (obj.isDone === false) {// 체크 안 된 상태
        btn.addEventListener("click", doneToDo);
    }
    else if (obj.isDone === true) {// 체크 된 상태 (all)
        btn.addEventListener("click", againToDo);
        li.classList.add("complete")
    }
}

function paintToDo(text) {   // 사용자 입력값 화면에 그려주기 //active
    const li = document.createElement("li");
    const btn = document.createElement("button");
    btn.addEventListener("click", doneToDo);
    const span = document.createElement("span");
    span.addEventListener("click", editTodo);
    const newId = allToDo.length + 1;
    if (!noValue(text)) {
        span.innerText = text;
        li.appendChild(btn);
        li.appendChild(span);
        li.id = newId;
        toDoList.appendChild(li);
        const toDoObj = {
            text: text,
            id: newId,
            isDone: false
        };
        allToDo.push(toDoObj);
        activeToDo.push(toDoObj);
        saveToDos();
    }
}

function compPaint(obj) {
    const li = document.createElement("li");
    const btn = document.createElement("button");
    btn.addEventListener("click", againToDo); search
    const span = document.createElement("span");
    span.addEventListener("click", editTodo);
    span.innerText = obj.text;
    li.appendChild(btn);
    li.appendChild(span);
    li.id = obj.id;
    li.classList.add("complete");
    toDoList.appendChild(li);
}

function againToDo(event) {
    const bnt = event.target;
    const li = bnt.parentNode;
    if (h1.innerText === "All") {//추가 - 210113 이수민
        if (li.classList.contains("complete")){
            li.classList.remove("complete");
        }
        else{
            li.classList.add("complete");
        }
    } else
        toDoList.removeChild(li);
    const d = allToDo.find(toDo => toDo.id === parseInt(li.id));
    if(d.isDone){
        d.isDone = false;
    }
    else {
        d.isDone = true;
    }
    const idx = allToDo.findIndex(toDo => toDo.id === parseInt(li.id)); // 
    if (idx > -1) completedToDo.splice(idx, 1);
    saveToDos();
    loadTodos();
}


function doneToDo(event) {
    const bnt = event.target;
    const li = bnt.parentNode;
    if (h1.innerText === "All") {//추가 - 210113 이수민
        if (li.classList.contains("complete")){
            li.classList.remove("complete");
        }
        else {
            li.classList.add("complete");
        }
    } else
        toDoList.removeChild(li);
    const d = allToDo.find(toDo => toDo.id === parseInt(li.id));
    if(d.isDone){
        d.isDone = false;
    }
    else {
        d.isDone = true;
    }
    const idx = allToDo.findIndex(toDo => toDo.id === parseInt(li.id)); // 
    if (idx > -1) activeToDo.splice(idx, 1);
    saveToDos();
    loadTodos();
}

function editTodo(event) {
    const span = event.target;
    const li = span.parentNode;
    const d = allToDo.find(toDo => toDo.id === parseInt(li.id));
    // span display none -> input block -> 글자 수정 -> 엔터 ( 저장 )
    span.style.display = 'none';
    const form = document.createElement("form");
    const input = document.createElement("input");
    input.type = "text";
    input.value = d.text;
    input.autofocus;
    input.style = "color: white; border: none; background: transparent;  font-size: 30px;";
    li.appendChild(form);
    form.appendChild(input);
    form.addEventListener("submit", function () {
        event.preventDefault();
        if (!noValue(input.value)) {
            d.id = d.id;
            d.text = input.value;
            span.innerText = input.value;
        };
        li.removeChild(form);
        span.style.display = 'block';
        //  저장 
        input.value = "";
        toDoForm.style.display = "none";
        saveToDos();
    });

}

function deleteToDo(event) {              /*  complete에서 완전 삭제 어떤 방식으로 할지 상의 : 전체 삭제  */
    while (toDoList.hasChildNodes()) {
        toDoList.lastChild.remove();
    };
    allToDo = allToDo.filter(todo => !completedToDo.includes(todo));
    completedToDo = [];
    saveToDos();
    loadTodos();
}


let valueCount = [];
//검색// 210114 이수민 추가
function filter() {
    valueCount = [];
    const value = document.getElementById("search").value.toUpperCase();
    for (let i = 0; i < toDoList.childElementCount; i++) {
        if (toDoList.children[i].innerText.toUpperCase().indexOf(value) > -1) {
            toDoList.children[i].style.display = "flex";
            valueCount.push(value);
        } else
            toDoList.children[i].style.display = "none";
    }
}

function show_input() {
    toDoForm.style.display = "block";
}

m_gnb.addEventListener("click", viewSidebar);
// 모바일 - 사이드바 열기
function viewSidebar() {
    document.getElementById("content").style.display = "none";
    document.getElementById("sidebar").style.display = "block";
    openview = true;
}

//값 유효성검사  이수민
function noValue(value) {
    if (value == " " || value == "" || value == null || value == undefined || (value != null && typeof value == "object" && !Object.key(value).length))
        return true;
    else
        return false;
}

// body안에 전체를 감싸고 있는 wrap을 div를 객체생성 -> 클릭시 갯수를 출력하는 
// showCount를 호출 
//210113 양수정 수정
function showCount() {
    document.getElementById("wrap").addEventListener("click", showCount);
    let toDocount = todoheader.firstElementChild;
    let alleng = allToDo.length;
    let active = activeToDo.length;
    let compl = completedToDo.length;
    if (h1.textContent == "Active")
        toDocount.textContent = active;
    else if (h1.textContent == "Completed")
        toDocount.textContent = compl;
    else if (h1.textContent == "All")
        toDocount.textContent = alleng;
    document.getElementById("all").textContent = alleng;
    document.getElementById("active").textContent = active;
    document.getElementById("completed").textContent = compl;
}



function changeMenu(menu, color, count) {
    const newh1 = document.createElement("h1");
    newh1.innerText = menu;
    todoheader.replaceChild(newh1, h1);
    todoheader_count.textContent = count;
    h1 = newh1;
    h1.style.color = color;
    todoheader_count.style.color = color;
    while (toDoList.hasChildNodes()) {
        toDoList.lastChild.remove();
    };
    addEmpty();

    loadTodos();

}

//210114 양수정 추가
function addEmpty() {
    if (h1.innerText === "Completed")    // completed 를 제외한 모든 메뉴에선 del_btn 없엔다. 
        del_btn.style.display = "block";
    else
        del_btn.style.display = "none";

    if (h1.textContent == "Active" || h1.textContent == "All") {  // All 과 Active 일때만 empty와 puls활성 
        clkEmp.style.display = "block";
        plus.style.display = 'block';
    } else {
        clkEmp.style.display = "none";
        toDoForm.style.display = "none";
        plus.style.display = 'none';
    }  
    if(h1.innerText == "Developer"){
        $("#member-Area").show();
    }else{
        $("#member-Area").hide();
    }
}

function only_m() {
    if (openview = true) {
        document.getElementById("content").style.display = "block";
        document.getElementById("sidebar").style.display = "none";
    }
    document.getElementById("content").style.display = "block";
    document.getElementById("sidebar").style.display = "block";

}

