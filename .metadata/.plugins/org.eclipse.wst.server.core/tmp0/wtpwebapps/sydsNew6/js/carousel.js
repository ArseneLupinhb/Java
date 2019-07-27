        //页面一加载完就执行
        window.onload = function () {
            //获取节点
            var container = document.getElementById('container');
            var list = document.getElementById('list');
            var buttons = document.getElementById('buttons').getElementsByTagName('span');//获取小圆点（五个）
            var prev = document.getElementById('prev');
            var next = document.getElementById('next');
            var index = 1; //第几个小圆点
            
            //animate函数，用来切换图片
            function animate (offset) {
                var newLeft=parseInt(list.style.left)+offset;

                list.style.left=newLeft+'px'; //改变list的left属性
                //当右箭头点击到最后一张图
                if(newLeft<-5600){
                    list.style.left=0+'px';
                }
                //当左箭头点击到第一张图
                if(newLeft>0){
                    list.style.left=-5600+'px';
                }
            }

            //改变小圆点的颜色
            function showButton() {
                for (var i = 0; i < buttons.length ; i++) {
                    if( buttons[i].className == 'on'){
                        buttons[i].className = '';
                        break;
                    }
                }
                buttons[index - 1].className = 'on'; //buttons[i]从零开始的
            }


            //右箭头
            next.onclick = function () {
                //判断，index不大于5
                if (index == 5) {
                    index = 1;
                }
                else {
                    index += 1;
                }
                animate(-1400);//调用，实现图片切换
                showButton();//调用，改变小圆点
            }

            //左箭头
            prev.onclick = function () {
                //判断，index不小于1
                if (index == 1) {
                    index = 5;
                }
                else {
                    index -= 1;
                }
                animate(1400);
                showButton();
            }

             //点击小圆点
            for (var i = 0; i < buttons.length; i++) {
                buttons[i].onclick = function () {
                    var myIndex = parseInt(this.getAttribute('index'));
                    var offset = -1400 * (myIndex - index);

                    animate(offset);
                    index = myIndex; //更新index的值
                    showButton();
                }
            }

            //自动切换
            var timer=setInterval(function(){
            	next.onclick();
            },4000);


        }