/*
 * Challenge: https://www.codewars.com/kata/551dc350bf4e526099000ae5/javascript
 */

function songDecoder(song) {
    str = "";
    for (let i = 0; i < song.length; i++) {
        if (song[i] === "W") {
            if (song.substring(i, i + 3) === "WUB") {
                if (str[str.length - 1] !== " ") {
                    str += " ";
                }
                i += 2;
            } else {
                str += song[i];
            }
        } else {
            str += song[i];
        }
    }
    return str.trim();
}