$(
    function () {

        let index = 0

        window.loadOrders = function () {
            switch (index) {
                case 0:
                    window.loadAllOrders();
                    break;
                case 1:
                    window.loadTodoOrders();
                    break;
            }
        }

        function bindClick() {
            $('.nav-wrapper li').on('click', function () {
                $(this).addClass('active').removeClass('unactive')
                    .siblings().removeClass('active').addClass('unactive');

                index = $(this).index();
                switch (index) {
                    case 0:
                        $('.refresh-container').hide();

                        if (!$('#all-orders').hasClass('loaded')) {
                            $('#all-orders').load('./allOrders/allOrders.html ', function () {
                                window.loadAllOrders();
                            });
                            $('#all-orders').addClass('loaded');
                        }

                        $('#all-orders').show()
                            .siblings().hide();
                        break;
                    case 1:
                        $('.refresh-container').show();

                        if (!$('#todo-orders').hasClass('loaded')) {
                            $('#todo-orders').load('./todoOrders/todoOrders.html ', function () {
                                window.loadTodoOrders();
                            });
                            $('#todo-orders').addClass('loaded');
                        }

                        $('#todo-orders').show()
                            .siblings().hide();
                        break;
                }
            });

            $('.add-order').on('click', function () {
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

            $('.refresh-container .refresh-icon').on('click', function () {
                window.loadTodoOrders();
            });

            $('.search-wrapper .search-content').bind('keydown', function (e) {
                // enter key
                if (e.keyCode == '13') {
                    const name = $(this).val();
                    switch (index) {
                        case 0:
                            window.loadAllOrders(name);
                            break;
                        case 1:
                            window.loadTodoOrders(name);
                            break;
                    }
                }
            });
        }

        bindClick();
        $('.nav-wrapper li:nth-child(2)').trigger('click');
    }
);