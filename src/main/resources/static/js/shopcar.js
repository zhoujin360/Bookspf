var ul = document.querySelector("#cart_list").querySelector("ul");
var dels = document.querySelectorAll("#del");
var decrease = document.querySelectorAll("#decrease");
var add = document.querySelectorAll("#add");
var book_num = document.querySelectorAll("#book_num");
for (var i = 0; i < dels.length; i++) {
    dels[i].onclick = function () {
        ul.removeChild(this.parentNode);
    }
}
for (var i = 0; i < decrease.length; i++) {
    decrease[i].onclick = function () {
        this.nextElementSibling.value--;
        if (this.nextElementSibling.value < 1) {
            this.nextElementSibling.value = 0;
        }
    }
}
for (var i = 0; i < add.length; i++) {
    add[i].onclick = function () {
        this.previousElementSibling.value++;
        if (this.previousElementSibling.value >= 100) {
            alert("请联系客服");
        }
    }
}
