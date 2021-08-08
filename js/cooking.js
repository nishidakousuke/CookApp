(function cook() {
    let process_count = 1;
    let count_clear;

    function onePrevNextElementGet(num) {
        let now_elem = document.getElementById(`process${process_count}`);
        now_elem.style.display = "none";
        let new_elem = document.getElementById(`process${process_count + num}`);
        new_elem.style.display = "block";
        let time_value = document.getElementById(`time${process_count}`);
        time_value.style.display = "none";
        process_count += num;
    }

    function onePrev(time_count, count_up) {
        return function(e) {
            if(process_count > 1) {
                onePrevNextElementGet(-1);
                time_value_check(time_count);
                countUpClear(count_up);
            }
        }
    };

    function oneNext(processes, time_count, count_up) {
        return function(e) {
            if(process_count < processes.length) {
                onePrevNextElementGet(1);
                time_value_check(time_count);
                countUpClear(count_up);
            }
        }
    }

    function time_value_check(time_count) {
        let time_value = document.getElementById(`time${process_count}`);
        if(time_value.innerHTML) {
            time_value.style.display = "block";
            time_count.style.display = "block";
        } else {
            time_count.style.display = "none";
        }
    }

    function countUp(count_up) {
        return function(e) {
            count_up.style.display = "block"
            let countup = 0;
            count_clear = setInterval(() => {
                count_up.innerHTML = `${countup}秒経過`;
                countup++;
            }, 1000);
        }
    }

    function countUpClear(count_up) {
        clearInterval(count_clear);
        count_up.innerHTML = "";
        count_up.style.display = "none";
    }

    window.onload = function() {
        let processes = document.getElementsByClassName("process");
        let time_count = document.getElementById("time_count");
        let count_up = document.getElementById("count_up");

        for(let i = 1; i < processes.length; i++) {
            processes[i].style.display = "none";
        }

        time_value_check(time_count);

        let prev = document.getElementById("prev");
        prev.addEventListener("click", onePrev(time_count, count_up), false);

        let next = document.getElementById("next");
        next.addEventListener("click", oneNext(processes, time_count, count_up), false);

        time_count.addEventListener("click", countUp(count_up), false);
    };
})();
