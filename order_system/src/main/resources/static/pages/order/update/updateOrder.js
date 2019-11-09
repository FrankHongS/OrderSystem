$(
    function () {
        // current updating order
        const order = parent.order;

        function bindOrderInfo() {
            $('#name').val(order.name);
            $('#phone-number').val(order.phoneNumber);
            $('#address').val(order.address);
            $('#sold-date').val(order.soldDate);
            $('#machine-type').val(order.machineType);
        }

        function bindClick() {
            $('.button-sure').on('click', function () {
                updateOrder();
            });

            $('.button-cancel').on('click', function () {
                closeFrame();
            });
        }

        function renderDate() {
            laydate.render({
                elem: '#sold-date',
                theme: '#393D49',
                trigger: 'click',
                btns: ['confirm']
            });
        }

        function updateOrder() {
            const name = $('#name').val();
            if (!name) {
                $('.message').text('请输入姓名');
                return;
            }
            $.ajax({
                url: '/ordersystem/order/update',
                type: 'POST',
                data: {
                    id: order.id,
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
                        $('.message').text('更新失败: ' + result.message);
                    }
                },
                error: (xhr, e) => {
                    $('.message').text('更新失败');
                }
            });
        }

        function closeFrame() {
            const index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }

        bindOrderInfo();
        bindClick();
        renderDate();
    }
);