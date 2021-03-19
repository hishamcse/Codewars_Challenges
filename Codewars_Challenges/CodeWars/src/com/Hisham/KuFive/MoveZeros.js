/*
 * Challenge: https://www.codewars.com/kata/52597aa56021e91c93000cb0/javascript
 */
var moveZeros = function (arr) {
    res = [];
    c = 0;
    for (let i = 0; i < arr.length; i++) {
        if (arr[i] === 0) {
            c++;
        } else {
            res.push(arr[i]);
        }
    }
    for (let i = 0; i < c; i++) {
        res.push(0);
    }
    return res;
};