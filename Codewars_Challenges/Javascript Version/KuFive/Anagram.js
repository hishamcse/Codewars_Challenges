/*
Challenge: https://www.codewars.com/kata/523a86aa4230ebb5420001e1
 */

function anagrams(word, words) {
    word = word.split("").sort().join("");
    let list = [];
    for (let i = 0; i < words.length; i++) {
        if (words[i].split("").sort().join("") === word) {
            list.push(words[i]);
        }
    }
    return list;
}

console.log(anagrams('abba',['aabb', 'abcd', 'bbaa', 'dada']))