//导航小人 
//右键菜单
jQuery(document).ready(function ($) {
    $("#spig").mousedown(function (e) {
        if(e.which==3){
        showMessage("我来给你引路！<br /><a href=\"./login.htm\" title=\"登陆系统\" style=\"font-size: 12px;text-decoration: none;\">登陆系统</a></br><a href=\"./init.do?init\" title=\"返回首页\" style=\"font-size: 13px;text-decoration: none;\">返回首页</a>",10000);
}
});
$("#spig").bind("contextmenu", function(e) {
    return false;
});
});

//鼠标在消息上时
jQuery(document).ready(function ($) {
    $("#message").hover(function () {
       $("#message").fadeTo("100", 1);
     });
});


//鼠标在上方时
jQuery(document).ready(function ($) {
    //$(".mumu").jrumble({rangeX: 2,rangeY: 2,rangeRot: 1});
    $(".mumu").mouseover(function () {
       $(".mumu").fadeTo("300", 0.3);
       msgs = ["我隐身了，你看不到我，好好看视频！", "我会隐身哦！嘿嘿！，好好看视频！", "别动手动脚的，把手拿开！，好好看视频！", "把手拿开我才出来！，好好看视频！","流氓别摸我!，好好看视频！"];
       var i = Math.floor(Math.random() * msgs.length);
        showMessage(msgs[i]);
    });
    $(".mumu").mouseout(function () {
        $(".mumu").fadeTo("300", 1);
    });
});

//开始
jQuery(document).ready(function ($) {
    if (isindex) { //如果是主页
        var now = (new Date()).getHours();
        if (now > 0 && now <= 6) {
            showMessage(/*visitor +*/ '亲， 你是夜猫子呀？还不睡觉，明天起的来么你？', 6000);
        } else if (now > 6 && now <= 11) {
            showMessage(/*visitor +*/ '亲， 早上好，早起的鸟儿有虫吃噢！早起的虫儿被鸟吃，你是鸟儿还是虫儿？嘻嘻！', 6000);
        } else if (now > 11 && now <= 14) {
            showMessage(/*visitor +*/  '亲， 中午了，吃饭了么？不要饿着了，饿死了谁来挺我呀！', 6000);
        } else if (now > 14 && now <= 18) {
            showMessage(/*visitor +*/ '亲， 中午的时光真难熬！还好有你在！', 6000);
        } else {
            showMessage(/*visitor +*/ '亲， 快来逗我玩吧！', 6000);
        }
    }
    else {
        showMessage('在线影音欣赏', 6000);
    }
    $(".spig").animate({
        top: $(".spig").offset().top + 300,
        left: document.body.offsetWidth - 160
    },
	{
	    queue: false,
	    duration: 1000
	});
});




var spig_top = 50;
//滚动条移动
jQuery(document).ready(function ($) {
    var f = $(".spig").offset().top;
    $(window).scroll(function () {
        $(".spig").animate({
            top: $(window).scrollTop() + f +300
        },
		{
		    queue: false,
		    duration: 1000
		});
    });
});

//鼠标点击时
jQuery(document).ready(function ($) {
    var stat_click = 0;
    $(".mumu").click(function () {
        if (!ismove) {
            stat_click++;
            if (stat_click > 4) {
                msgs = ["你有完没完呀？", "你已经摸我" + stat_click + "次了", "非礼呀！救命！OH，My ladygaga"];
                var i = Math.floor(Math.random() * msgs.length);
                //showMessage(msgs[i]);
            } else {
                msgs = ["筋斗云！~我飞！", "我跑呀跑呀跑！~~", "别摸我，大男人，有什么好摸的！", "惹不起你，我还躲不起你么？", "不要摸我了，我会告诉老婆来打你的！", "干嘛动我呀！小心我咬你！"];
                var i = Math.floor(Math.random() * msgs.length);
                //showMessage(msgs[i]);
            }
        s = [0.1, 0.2, 0.3, 0.4, 0.5, 0.6,0.7,0.75,-0.1, -0.2, -0.3, -0.4, -0.5, -0.6,-0.7,-0.75];
        var i1 = Math.floor(Math.random() * s.length);
        var i2 = Math.floor(Math.random() * s.length);
            $(".spig").animate({
            left: document.body.offsetWidth/2*(1+s[i1]),
            top:  document.body.offsetHeight/2*(1+s[i1])
            },
			{
			    duration: 500,
			    complete: showMessage(msgs[i])
			});
        } else {
            ismove = false;
        }
    });
});
//显示消息函数 
function showMessage(a, b) {
    if (b == null) b = 10000;
    jQuery("#message").hide().stop();
    jQuery("#message").html(a);
    jQuery("#message").fadeIn();
    jQuery("#message").fadeTo("1", 1);
    jQuery("#message").fadeOut(b);
};

//拖动
var _move = false;
var ismove = false; //移动标记
var _x, _y; //鼠标离控件左上角的相对位置
jQuery(document).ready(function ($) {
    $("#spig").mousedown(function (e) {
        _move = true;
        _x = e.pageX - parseInt($("#spig").css("left"));
        _y = e.pageY - parseInt($("#spig").css("top"));
     });
    $(document).mousemove(function (e) {
        if (_move) {
            var x = e.pageX - _x; 
            var y = e.pageY - _y;
            var wx = $(window).width() - $('#spig').width();
            var dy = $(document).height() - $('#spig').height();
            if(x >= 0 && x <= wx && y > 0 && y <= dy) {
                $("#spig").css({
                    top: y,
                    left: x
                }); //控件新位置
            ismove = true;
            }
        }
    }).mouseup(function () {
        _move = false;
    });
});


//右侧qq
function myEvent(obj,ev,fn){
	if (obj.attachEvent){
		obj.attachEvent('on'+ev,fn);
	}else{
		obj.addEventListener(ev,fn,false);
	};
};
function getbyClass(id,sClass){
	var oParent = document.getElementById(id);
	var all = oParent.getElementsByTagName('*');
	var array = [];
	for (var i=0; i<all.length; i++){
		if (all[i].className == sClass){
			array.push(all[i]);
		};
	};
	return array;
};
function getStyle(obj,name){
	if(obj.currentStyle){
		return obj.currentStyle[name];
	}else{
		return getComputedStyle(obj,false)[name];
	};
};
function Running(obj,json,fnEnd){
	clearInterval(obj.timer);
	obj.timer=setInterval(function(){
		var now=0;
		var bStop=true;
		for (var attr in json){
			if(attr=='opacity'){
				now=Math.round(parseFloat(getStyle(obj,attr))*100);
			}else{
				now=parseInt(getStyle(obj,attr));
			};
			var speed=(json[attr]-now)/5;
			speed=speed>0?Math.ceil(speed):Math.floor(speed);
			if(now!=json[attr])bStop=false;
			if(attr=='opacity'){
				obj.style.filter='alpha(opacity:'+now+speed+')';
				obj.style.opacity=(now+speed)/100;
			}else{
				obj.style[attr]=speed+now+'px';
			};
		}
		if(bStop){
			clearInterval(obj.timer);
			if(fnEnd)fnEnd();
		}
	}, 30);
}
function Flexing(obj,json,fnEnd){
	clearInterval(obj.timer);
	obj.timer=setInterval(function(){
		var now=0;
		var bStop=true;
		for (var attr in json){
			if(!obj.speed)obj.speed={};
			if(!obj.speed[attr])obj.speed[attr]=0;
			now=parseInt(getStyle(obj,attr));
			if(Math.abs(json[attr]-now)>1 || Math.abs(obj.speed[attr])>1){
				bStop=false;
				obj.speed[attr]+=(json[attr]-now)/5;
				obj.speed[attr]*=0.85;
				var MaxSpeed=65;
				if(Math.abs(obj.speed[attr])>MaxSpeed){
					obj.speed[attr]=obj.speed[attr]>0?MaxSpeed:-MaxSpeed;
				};
				obj.style[attr]=now+obj.speed[attr]+'px';
			};
		};
		if(bStop){
			clearInterval(obj.timer);
			obj.style[attr]=json[attr]+'px';
			if(fnEnd)fnEnd();
		};
	}, 30);
}
function setqq(obj,num){
	if (obj.length!=num.length){
		alert('\nspan鐨勪釜鏁颁笌QQ鍙风爜鐨勪釜鏁颁笉绗︼紝璇疯缃�涓猀Q鍙风爜!\n\n[璁剧疆鏈垚鍔�]');
		return;
	}else{
		for (var i=0; i<num.length; i++){
			obj[i].innerHTML = "<a target='_blank' href='http://wpa.qq.com/msgrd?v=3&uin="+num[i]+"&site=qq&menu=yes'><img border='0' src='http://wpa.qq.com/pa?p=2:"+num[i]+":51' alt='鐐瑰嚮鍜ㄨ' title='鐐瑰嚮鍜ㄨ'/></a>";
		};
	};
};
function settop(id,id2,top){
	var obj = document.getElementById(id);
	var box = document.getElementById(id2);
	obj.style.top = box.style.top = top+'px';
};
function dealy(id,time){
	var obj = document.getElementById(id);
	var timer = setTimeout(function(){
		Flexing(obj,{right:-100});
	},time*1000);
};
function click_fn(id,id2){
	var obj = document.getElementById(id);
	var box = document.getElementById(id2);
	obj.onclick = function(){
		Running(obj,{right:-200},function(){
			box.style.display = 'block';
			Running(box,{right:10, opacity:100});					
		});
	};
	box.onclick = function(){
		timer = setTimeout(function(){
			Running(box,{right:-220,opacity:0},function(){
				box.style.display = 'none';
				Flexing(obj,{right:-100});
			});			
		},3000);
	};
};
 