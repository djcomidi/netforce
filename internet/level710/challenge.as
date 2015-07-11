//----------------------------------------------------------------------
//Frame 3
//----------------------------------------------------------------------
    function decrypt(ds) {
        temp = "";
        i = 0;
        i = ds.length;
        while (i >= 0) {
            temp = temp + ds.charAt(i);
            i--;
        }
        ds = temp;
        temp = "";
        i = 0;
        i = 0;
        while (i < ds.length) {
            temp = temp + String.fromCharCode(ds.charCodeAt(i) - 1);
            i++;
        }
        return(temp);
    }
    function scoreHandle(sc) {
        sc = String(sc);
        sc = sc.split("");
        sc.reverse();
        sc = sc.join("");
        temp = "";
        i = 0;
        while (i <= sc.length) {
            switch (sc.charAt(i)) {
                case "0" : 
                    temp = temp + "A";
                    break;
                case "1" : 
                    temp = temp + "B";
                    break;
                case "2" : 
                    temp = temp + "C";
                    break;
                case "3" : 
                    temp = temp + "D";
                    break;
                case "4" : 
                    temp = temp + "E";
                    break;
                case "5" : 
                    temp = temp + "F";
                    break;
                case "6" : 
                    temp = temp + "G";
                    break;
                case "7" : 
                    temp = temp + "H";
                    break;
                case "8" : 
                    temp = temp + "I";
                    break;
                case "9" : 
                    temp = temp + "J";
            }
            i++;
        }
        sc = temp;
        ldScr = new LoadVars();
        ldScr.score = sc;
        ldScr.sendAndLoad(decrypt("qiq/hojttpmqPfe"), ldScr, POST);
        ldScr.onLoad = function (success) {
            if (success) {
                _root.comments = this.comments;
            }
        };
    }
    score = _global.score;
    scoreHandle(score);


//----------------------------------------------------------------------
//Frame 2
//----------------------------------------------------------------------
    function addBlokje(nieuweNaam, x, y, alpha) {
        z = _root.z;
        _root.hetBlokje.duplicateMovieClip(nieuweNaam, z);
        temp = eval (nieuweNaam);
        temp._x = x;
        temp._y = y;
        temp._visible = alpha;
        _root.z = _root.z + 1;
    }
    function setBlokjes() {
        _root.hetBlokje._visible = false;
        i = 0;
        _root.z = 2;
        i = 0;
        while (i <= 44) {
            j = 0;
            j = 0;
            while (j <= 24) {
                addBlokje((("B" + i) + "-") + j, 10 * (i + 1), 10 * (j + 2), false);
                j++;
            }
            i++;
        }
    }
    function setVars() {
        _root.died = false;
        _root.started = false;
        _root.verlengen = false;
        _root.richting = 1;
        _root.BC = new Object();
        _global.score = 0;
        _root.score = 0;
        _root.comment = "TurboSnake! Get exactly 40.000 points to get the password :) \n\nPress <<UP>> to start!";
        _root.deSnake = [];
        _root.deSnake.push({x:20, y:17});
        _root.deSnake.push({x:20, y:18});
        _root.deSnake.push({x:20, y:19});
        _root.deSnake.push({x:20, y:20});
        _root.deSnake.push({x:20, y:21});
        i = 0;
        i = 0;
        while (i < 5) {
            tA = _root.deSnake[i];
            eval ((("B" + tA.x) + "-") + tA.y)._visible = true;
            i++;
        }
    }
    function getRichting() {
        vorige = _root.richting;
        if (Key.isDown(38)) {
            _root.richting = 1;
            ((vorige == 3) ? ((_root.died = true)) : 0);
        } else if (Key.isDown(39)) {
            _root.richting = 2;
            ((vorige == 4) ? ((_root.died = true)) : 0);
        } else if (Key.isDown(40)) {
            _root.richting = 3;
            ((vorige == 1) ? ((_root.died = true)) : 0);
        } else if (Key.isDown(37)) {
            _root.richting = 4;
            ((vorige == 2) ? ((_root.died = true)) : 0);
        }
    }
    function moveSnake() {
        temp = new Object();
        temp2 = new Object();
        tempArray = [];
        temp.x = _root.deSnake[0].x;
        temp.y = _root.deSnake[0].y;
        temp2.x = _root.deSnake[_root.deSnake.length - 1].x;
        temp2.y = _root.deSnake[_root.deSnake.length - 1].y;
        if (!_root.verlengen) {
            _root.deSnake.pop();
        }
        if (_root.richting == 1) {
            temp.y = temp.y - 1;
        } else if (_root.richting == 2) {
            temp.x = temp.x + 1;
        } else if (_root.richting == 3) {
            temp.y = temp.y + 1;
        } else if (_root.richting == 4) {
            temp.x = temp.x - 1;
        }
        if (temp.x < 0) {
            temp.x = 44;
        } else if (temp.x > 44) {
            temp.x = 0;
        } else if (temp.y < 0) {
            temp.y = 24;
        } else if (temp.y > 24) {
            temp.y = 0;
        }
        if (eval ((("B" + temp.x) + "-") + temp.y)._visible == 1) {
            if ((temp.x == _root.BC.x) && (temp.y == _root.BC.y)) {
                _global.score = _global.score + 10;
                _root.score = _global.score;
                _root.verlengen = true;
            } else {
                _root.died = true;
            }
        }
        tempArray = [temp];
        _root.deSnake = tempArray.concat(_root.deSnake);
        if (!_root.verlengen) {
            eval ((("B" + temp2.x) + "-") + temp2.y)._visible = false;
        } else {
            _root.deSnake.push({x:temp2.x, y:temp2.y});
            _root.verlengen = false;
            randomBlokje();
        }
        eval ((("B" + temp.x) + "-") + temp.y)._visible = true;
    }
    function randomBlokje() {
        eval ((("B" + _root.BC.x) + "-") + _root.BC.y)._visible = 0;
        condition = true;
        while (condition) {
            x = Math.round(Math.random() * 44);
            y = Math.round(Math.random() * 24);
            if (eval ((("B" + _root.BC.x) + "-") + _root.BC.y)._visible != true) {
                condition = false;
            }
        }
        _root.BC.x = x;
        _root.BC.y = y;
        eval ((("B" + x) + "-") + y)._visible = true;
    }
    function reset() {
        ding = _root.BC;
        eval ((("B" + BC.x) + "-") + BC.y)._visible = 0;
        i = 0;
        i = 0;
        while (i < _root.deSnake.length) {
            eval ((("B" + _root.deSnake[i].x) + "-") + _root.deSnake[i].y)._visible = 0;
            i++;
        }
    }
    stop();
    setBlokjes();
    setVars();
    _root.onEnterFrame = function () {
        if (_root.started && (!_root.died)) {
            eval ((("B" + _root.BC.x) + "-") + _root.BC.y)._visible = true;
            getRichting();
            moveSnake();
        } else if (_root.died) {
            reset();
            _root.comment = "Died!";
            gotoAndStop (3);
        } else if (Key.isDown(38)) {
            _root.started = true;
            _root.comment = "";
            randomBlokje();
        }
    };


//----------------------------------------------------------------------
//Symbol 17 Button
//----------------------------------------------------------------------
on (release) {
    getURL ("", );
}

//----------------------------------------------------------------------
//Symbol 18 Button
//----------------------------------------------------------------------
on (release) {
    getURL ("http://www.net-force.nl/members/view/775/");
}

//----------------------------------------------------------------------
//Symbol 15 Button
//----------------------------------------------------------------------
on (release) {
    reset();
    setVars();
}

//----------------------------------------------------------------------
//Symbol 16 Button
//----------------------------------------------------------------------
on (release) {
    getURL ("http://www.net-force.nl/", "_self");
}

//----------------------------------------------------------------------
//Symbol 27 Button
//----------------------------------------------------------------------
on (release) {
    gotoAndStop (2);
}
