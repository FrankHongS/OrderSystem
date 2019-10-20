$(
    function(){
        $('.add-order').on('click',function(){
            layer.open({
                type: 2,
                title: '添加新订单',
                area: ['860px', '430px'],
                fix: false,
                maxmin: false,
                scrollbar: false,
                content: '../order/add/addOrder.html'
            });
        });

        $('.post-sold-detail').bind('click',function(e){
            alert('售后细节'+$(e.target).parent().index());
        });
    }
);