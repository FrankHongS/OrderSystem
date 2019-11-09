$(
    function () {
        function bindClick() {
            $('.button-sure').on('click', function () {
                saveOrder();
            });

            $('.button-cancel').on('click', function () {
                closeFrame();
            });
        }

        function renderDate(){
            laydate.render({
                elem: '#sold-date',
                theme: '#393D49',
                trigger: 'click',
                btns: ['confirm']
            });
        }

        function saveOrder(){
            const name=$('#name').val();
            if(!name){
                $('.message').text('请输入姓名');
                return;
            }
            $.ajax({
                url: '/ordersystem/order/save',
                type: 'POST',
                data: {
                    name: $('#name').val(),
                    phoneNumber: $('#phone-number').val(),
                    address: $('#address').val(),
                    soldDate: $('#sold-date').val(),
                    machineType: $('#machine-type').val()
                },
                success: result => {
                    if (result.code == 0) {
                        closeFrame();
                        parent.loadOrders();
                    } else {
                        $('.message').text('保存失败: ' + result.message);
                    }
                },
                error: (xhr, e) => {
                    $('.message').text('保存失败');
                }
            });
        }

        function closeFrame() {
            const index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }

        bindClick();
        renderDate();
    }
);