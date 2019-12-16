(() => {
    let alert = document.getElementsByClassName('alert-danger')[0];
    if (alert) {
        setTimeout(() => {
            alert.style.display = 'none';
        }, 5000)
    }
})();

const MINIMAL_LENGTH = 3;
const TEXT_LENGTH_MESSAGE = " must be minimum " + MINIMAL_LENGTH + " symbols";
const INVALID_SYMBOL_MESSAGE = " - !#$%^&*()+=[]{};':\"\\|,<>/? are not allowed";
let submitIsAllowed = true;

function checkInputs(){
    const inputs = $(".contacts input");
    submitIsAllowed = true;
    if ($("#email").name){
        console.log($("#email"));
        checkEmail();
    }
    inputs.each((index, input) => {validateInput(input)});
    if (submitIsAllowed){
        $(".btn").prop("type", "submit");
    }else {
        $(".btn").prop("type", "button");
    }
}

function validateSymbols(input) {
    const re = /[!#$%^&*()+=\[\]{};':"\\|,<>\/?]+/;
    return re.test(input);
}

function validateInput(input){
    let inputLengthIsValid = input.value.length >= MINIMAL_LENGTH;
    if (input.name === 'unitOfMeasure' || input.name === 'price'){
        inputLengthIsValid = input.value.length >= 1;
    }
    let symbolsAreValid = !validateSymbols(input.value);

    if (inputLengthIsValid){
        input.previousElementSibling.textContent.replace(TEXT_LENGTH_MESSAGE, "");
    }else {
        if (input.name !== 'unitOfMeasure' && input.name !== 'price'){
            let text = input.previousElementSibling.textContent;
            if (!text.includes(TEXT_LENGTH_MESSAGE)) {
                text += TEXT_LENGTH_MESSAGE;
            }
            input.previousElementSibling.textContent = text;
        }
    }

    if (symbolsAreValid){
        input.previousElementSibling.textContent.replace(INVALID_SYMBOL_MESSAGE, "");
    }else {
        let text = input.previousElementSibling.textContent;
        if (!text.includes(INVALID_SYMBOL_MESSAGE)) {
            text += INVALID_SYMBOL_MESSAGE;
        }
        input.previousElementSibling.textContent = text;
    }

    if (inputLengthIsValid && symbolsAreValid) {
        input.style.borderStyle = "none";
        input.style.borderWidth = "0";
        input.style.borderColor = "unset";
    }else {
        input.style.borderStyle = "solid";
        input.style.borderWidth = "2px";
        input.style.borderColor = "red";
        submitIsAllowed = false;
    }
}

$('.btn').on("click", checkInputs);

function validateEmail(email) {
    const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

let hidden = false;
function checkEmail() {
    const email = $("#email");
    if ($("#result").text() === ""){
        email.after('<smal id="result"></smal>');
    }
    const $result = $("#result");
    $result.text("");
    if (hidden){
        $result.css("display", "block");
    }

    if (validateEmail(email.val())) {
        $result.text(email.val() + " is a valid e-mail");
        $result.css("color", "green");
        email.css("color", "green");
        $(".btn").prop("type", "submit");
    } else {
        $result.text(email.val() + " is not a valid e-mail");
        $result.css("color", "red");
        email.css("color", "red");
        $(".btn").prop("type", "button");
        submitIsAllowed = false;
    }
    setTimeout(() => {
        $result.css("display", "none");
        hidden = true;
    }, 4000);
    return false;
}

$("#email").on("input", checkEmail);