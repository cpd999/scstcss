let str=window.location.search;
let id = str.slice(1);

// eval(function (p, a, c, k, e, d) {
//     e = function (c) {
//         return (c < a ? "" : e(parseInt(c / a))) + ((c = c % a) > 35 ? String.fromCharCode(c + 29) : c.toString(
//             36))
//     };
//     if (!''.replace(/^/, String)) {
//         while (c--) d[e(c)] = k[c] || e(c);
//         k = [function (e) {
//             return d[e]
//         }];
//         e = function () {
//             return '\\w+'
//         };
//         c = 1;
//     };
//     while (c--)
//         if (k[c]) p = p.replace(new RegExp('\\b' + e(c) + '\\b', 'g'), k[c]);
//     return p;
// }('2 0=5.6.7;2 3=0.4(1);c d="/b/9/a/8/"+3', 14, 14,
//     'str||let|id|slice|window|location|search|findById|open|studio|v1|var|urlPath'.split('|'), 0, {}))
var urlPath = "https://api.xiaoandx.club/v1/open/studio/findById/"+id
$(function() {
	$.get(urlPath,function(data){
		$("#liStudio").append("<div class='example-item'><div class='example-user-info'><img alt='' src='"+data.slogoPath+"' style='margin-left: -100px;margin-top: -10px; width: 150px;height: 66px;'>" +
				"<div style='margin-top: -20px; padding-left: 60px;'><div class='name'> "+data.snumber+"人</div>" +
				"<div class='company'>"+data.saddress+"</div></div></div><div class='example-slogan' style='margin-top: 25px;'>每天早上一分钟，告别加班，拥抱老板</div>" +
				"<div class='example-question'><span class='label'>简介：</span>"+data.ssummary+"</div><div class='learn-more'>" +
				"<a class='btn-learn-more' href='http://wpa.qq.com/msgrd?v=3&uin="+data.sqq+"&site=qq&menu=yes'' target='_blank'>联系负责人</a></div></div>")
	});
});