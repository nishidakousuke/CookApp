(() => {

    let material_count = 4;

    // 登録する材料と量の数を増やす
    function MaterialAdd() {
        let element = document.getElementById("input");
        let param =  `材料<input type="text" name="material${material_count}">：量<input type="text" name="amount${material_count}"><br>`;
        element.insertAdjacentHTML('beforebegin', param);
        material_count++;
    };

    window.onload = function() {
        let button = document.getElementById("button");
        button.addEventListener("click", MaterialAdd, false);
    };


})();
