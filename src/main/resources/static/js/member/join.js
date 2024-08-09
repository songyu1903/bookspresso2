let $InputBox = document.querySelectorAll('.typoBox');
let $inputMsg = document.querySelectorAll('.inputMsg');
let $joinForm = document.querySelector("#join-form");
let $joinBtn = document.querySelector("#join-btn");

let $checkLoignId = document.getElementsByName("loginId");
let $successJoin = [];
/**
 * 아이디 중복 검사 후 에러 메세지 출력
  */
{
    let $inputLoginId = $InputBox[0];
    $inputLoginId.addEventListener('change', function (){

        let loginId = this.value;
        console.log("loginId = " + loginId);
        // let resultMsg = "";
        //유효성 검사

        fetch(`/join/check-loginId/${loginId}`, {method: 'POST'})
            .then(resp=> resp.text())
            .then(text => {
                if (text != 0){
                    // 이미 존재하는 아이디
                    $inputMsg[0].innerHTML = "이미 존재하는 아이디입니다. ";
                    $inputMsg[0].classList.add("error");

                }else{
                    $inputMsg[0].innerHTML = "사용가능한 ID입니다. ";
                    $successJoin[0] = true;

                }
            })
    })

}
/**
 * 비밀번호 유효성 검사
 * 8-16 자리 영문, 숫자, 특수문자 조합
 */
{
    let pwReg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/

    $InputBox[1].addEventListener('change', function () {
        let pw = this.value;

        console.log(pwReg.test(pw));
        if (pwReg.test(pw)){
            $inputMsg[1].innerText= "사용 가능한 비밀번호예요!";
            // $inputMsg[1].innerText.style.color = rgb(45, 101, 241);
        }
        else{
            console.dir($inputMsg[1])

            $inputMsg[1].innerText =document.querySelector("#passwordInvalid").innerText;
            console.log("sfsf#### = " + $inputMsg[1].innerText);
            $inputMsg[1].classList.add("error");
        }

    })

}

/**
 *  닉네임 중복 검사 후 에러 메세지
 */
{
    $InputBox[2].addEventListener('change', function(){
        let nickname = this.value;
        console.log("nickname = " + nickname);

        // 한글 인식 못함
        fetch(`/join/check-nickname?nickname=${nickname}`, {method : 'GET'})
            .then(resp => resp.text())
            .then(text =>{
                console.log(text);
                if (text != 0) {
                    $inputMsg[2].innerText = "사용 중인 닉네임입니다."
                    $inputMsg[2].classList.add("error");
                }else{
                    $inputMsg[2].innerText = "사용 가능한 닉네임입니다 :) ";
                    $successJoin[2] = true;
                    console.log("닉네임 중복 검사 통과@@");
                }
            })
    })
}


/**
 *  이메일 유효성 검사 (정규표현식)
 */
{
    let emailReg = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i;

    $InputBox[3].addEventListener('change', function (){

        let emailValue = this.value;
        console.log(emailValue);
        console.log(emailReg.test(emailValue));

        if (emailReg.test(emailValue)){
            fetch(`/join/check-email/${emailValue}`, {method:'POST'})
                .then(resp=>resp.text())
                .then(result =>{
                    if (result != 0) {
                        $inputMsg[3].innerHTML = "이미 존재하는 회원입니다.";
                        $inputMsg[3].classList.add("error");
                    }else{
                        $successJoin[3] = true;
                        console.log("이메일 입력 성공! == " + $successJoin);
                    }
                })
        }else{
            $inputMsg[3].innerHTML="잘못된 이메일 주소입니다. 이메일 주소를 정확하게 입력해주세요.";
            console.log($inputMsg[3].innerHTML);
            $inputMsg[3].classList.add("error");
        }
    })
}



// 안내 문자
// 아이디, 비밀번호
/**
 * 회원가입 정보 작성 데이터 중 아이디와 비밀번호를 입력하기 위해 input창을 클릭 시 자동으로 나오는
 * 아이디와 비밀번호의 작성 기준의 대한 메세지 출력 코드
 */

// 함수로 만들어서  개별 칸에 사용
{
    for (let i = 0; i < $InputBox.length; i++) {

        $InputBox[i].addEventListener('focus', function (){
            let inputName = this.getAttribute("name");
            console.log("inputName ::: "+inputName);

            let className = $inputMsg[i].getAttribute("class");
            console.log("class = " + className);

            let basicMsg = document.getElementById(inputName+"Msg").innerText;
            console.log('basicMsg',basicMsg);

            $inputMsg[i].innerHTML = basicMsg;

            if(className == "inputMsg basicMsg error" || className == "inputMsg error"){
                $inputMsg[i].classList.remove("error");
                console.log("class = " + className);
            }
        })
    }}

{
    for (let i = 0; i < $InputBox.length; i++) {
        $InputBox[i].addEventListener('blur', function (){
            let inputName = this.getAttribute("name");
            console.log("focusOut ::: "+inputName);
            if (!this.value){
                let errorMsg = document.getElementById(inputName+"Invalid").innerHTML;
                console.log('errorMsg', errorMsg);

                // document.getElementsByClassName('inputMsg')

                $inputMsg[i].classList.add('error');
                $inputMsg[i].innerHTML = errorMsg;
            }else{
                $inputMsg[i].innerHTML="";
            }
    })
}
}


/**
 * 모든 input 에 데이터를 입력해야만 submit 버튼이 활성화 되게 하는 코드
 */
{


     $joinBtn.addEventListener("click", function (){
         // let joinFormArray = Array.from($joinForm);
         //
         // for (let i = 0; i < joinFormArray.length-1; i++) {
         //     $successJoin[i] =Boolean(joinFormArray[i].value);
         //
         //     // console.log("num = " + joinFormArray.length);
         //     // console.log("name = " + joinFormArray[i].name);
         //     // console.log("value = " + joinFormArray[i].value );
         // }
         // console.log("array = " + successJoin);
         //
         // // let isBelowThreshold = (currentValue) => { currentValue = true;}
         // let joinResult = successJoin.every(result => result===true);
         //
         // console.log("성공여부 = " + joinResult);
         //
         // if (joinResult){
         //     $joinForm.submit()
         // }
        checkEmail();

     })
}






















