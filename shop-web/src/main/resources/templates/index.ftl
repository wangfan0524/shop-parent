<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script typet="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script>
        $.ajax({
            url:"http://localhost:8764/mobile/test",    //请求的url地址
            dataType:"json",   //返回格式为json
            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
            data:{"id":"123134"},    //参数值
            type:"POST",   //请求方式
            beforeSend:function(){
                //请求前的处理
            },
            success:function(result){
                alert(result);
                console.log(result)
                $("#h1").append(result.data)
                //请求成功时处理
            },
            complete:function(result){
                console.log(result)
                //请求完成的处理
            },
            error:function(){
                //请求出错处理
            }
        });


    </script>
</head>
<body>
<h1 id="h1">欢迎你，</h1>

</body>
</html>