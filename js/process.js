(function process() {

    document.onkeypress = function(e) {
        // エンターキーだったら無効にする
        if (e.key === 'Enter') {
          return false;
        }
    }

    let process_count = 4;

    function ProcessAdd() {
        let element = document.getElementById("process_input");
        let param = `工程：<input type="text" name="process${process_count}">時間：<input id="time${process_count}" type="text" name="time${process_count}"><br>`;
        element.insertAdjacentHTML('beforebegin', param);
        process_count++;
    };

    function OtherDelete(e) {
        if(e.target.parentElement.tagName === "P") {
            let id = e.target.parentElement.id;
            let cooks = document.getElementsByClassName("cook");

            for(let i = 0; i < cooks.length; i++) {
                if(cooks[i].parentElement.id !== id) {
                    cooks[i].parentElement.style.display =  "none";
                }
            }
        }
    };

    function AllDisplay() {
        let cooks = document.getElementsByClassName("cook");
        for(let i = 0; i < cooks.length; i++) {
            cooks[i].parentElement.style.display = "block";
        }
    }

    function timeEnterCheck() {
        let process_and_time = document.getElementById("process_and_time");
        let time_flag = true;
        let time_count = (process_and_time.children.length - 1) * 2 / 3 / 2;

        for(let i = 1; i <= time_count; i++) {
            let element = document.getElementById(`time${i}`);
            if(element.value !== "" && !(/^[0-9]+[秒分]$/.test(element.value))) {
                time_flag = false;
            }
        }

        let process_input = document.getElementById("process_input");

        if(time_flag) {
            process_input.style.display = "block";
        } else {
            process_input.style.display = "none";
        }

        requestAnimationFrame(timeEnterCheck);
    }

    window.onload = function() {
        let button = document.getElementById("button");
        button.addEventListener("click", ProcessAdd, false);

        let process_form = document.getElementById("process_form");
        process_form.addEventListener("click", OtherDelete, false);

        let cook_display = document.getElementById("cook_display");
        cook_display.addEventListener("click", AllDisplay, false);

        timeEnterCheck();
    }

})();
