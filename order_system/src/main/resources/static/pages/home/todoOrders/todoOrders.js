window.loadTodoOrders = function (name) {
    const order = {};
    const size = 10;
    // current page
    let page = localStorage.todoPage ? localStorage.todoPage : 0;
    // order item count
    let count = 0;

    let isFirstTime = true;
    let isJump = false;

    function fetchTodoOrders() {
        $.ajax({
            url: '/postsold/query/page',
            data: {
                page: page,
                size: size
            },
            success: result => {
                if (result.code == 0) {
                    displayData(result.data);
                } else {
                    console.error(result.message);
                }
            },
            error: (xhr, e) => {
                console.error(e);
            }
        });
    }

    function fetchTodoOrdersByName() {
        $.ajax({
            url: '/postsold/query/page/name',
            data: {
                page: page,
                size: size,
                name: name,
            },
            success: result => {
                if (result.code == 0) {
                    displayData(result.data);
                } else {
                    console.error(result.message);
                }
            },
            error: (xhr, e) => {
                console.error(e);
            }
        });
    }

    function displayData(data) {
        order.data = data.content;
        buildOrderTable('.todo-orders-container tbody', transformData(order.data));
        if (!isJump) {
            isFirstTime = true;
            count = data.count;
            buildPager();
        } else {
            isJump = false;
        }
    }

    /**
     * count: 总条数
     * limit: 每一页显示条数
     */
    function buildPager() {
        if (!count) {
            $('#todo-orders-pager-container').hide();
        } else {
            $('#todo-orders-pager-container').show();
        }

        layui.use(['laypage'], () => {
            const laypage = layui.laypage;
            laypage.render({
                elem: 'todo-orders-pager-container',
                count: count,
                limit: size,
                curr: page + 1,
                hash: 'page',
                groups: 10,
                layout: ['count', 'prev', 'next', 'page'],
                jump: obj => {

                    if (isFirstTime) {
                        isFirstTime = false;
                        return;
                    }

                    const curr = obj.curr;
                    // update current page
                    page = curr - 1;
                    localStorage.todoPage = page;
                    isJump = true;
                    fetchTodoOrders();
                }
            });
        });
    };

    function buildOrderTable(container, dataList) {

        if (dataList.length == 0) {
            $(container).empty();
            return;
        }

        const cellsArray = dataList.map(
            rowValues => {
                return rowValues.map(
                    (cell, index) => {
                        if (!cell) {
                            return $('<td>')
                                .text(cell);
                        }
                        if (index == 6) {
                            return $('<td>')
                                .addClass('post-sold-detail')
                                .text(cell);
                        } else if (index == 7) {
                            return $('<td>')
                                .append(cell);
                        } else {
                            return $('<td>')
                                .text(cell);
                        }
                    }
                );
            }
        );

        const rowsArray = cellsArray.map(
            (row, index) => {
                return $('<tr>')
                    .append(row);
            }
        );

        $(container).empty();
        $(container).append(rowsArray);

        // bind table click listeners
        bindTableClick();
    };

    function transformData(data) {
        const dataList = [];

        if (data.length == 0) {
            return dataList;
        }

        data.forEach((orderEle, index) => {
            const item = [];
            item.push(index + 1 + size * page);
            item.push(orderEle.name);
            item.push(orderEle.phoneNumber);
            item.push(orderEle.address);
            item.push(orderEle.soldDate);
            item.push(orderEle.machineType);
            item.push('······');
            item.push('<img class="edit icon" src="../../images/edit.png" alt="编辑" srcset="" title="编辑">' +
                '<img class="delete icon" src="../../images/delete.png" alt="删除" srcset="" title="删除">');
            dataList.push(item);
        });

        // item不够，填充空字符串
        if (dataList.length < size) {
            const delta = size - dataList.length;
            for (let i = 0; i < delta; i++) {
                dataList.push(['', '', '', '', '', '', '', '']);
            }
        }

        return dataList;
    }

    function bindTableClick() {
        $('.todo-orders-container .post-sold-detail').on('click', function (e) {
            const index = $(e.target).parent().index();
            window.open('../postsold/postsold.html?id='+order.data[index].id);
        });

        $('.todo-orders-container .edit').on('click', function (e) {
            // save current selected order to window for passing to iFrame 
            const index = $(e.target).parent().parent().index();
            window.order = order.data[index];
            layer.open({
                type: 2,
                title: '编辑订单',
                area: ['860px', '430px'],
                fix: false,
                maxmin: false,
                scrollbar: false,
                content: '../order/update/updateOrder.html'
            });
        });

        $('.todo-orders-container .delete').on('click', function (e) {
            if (confirm('删除该项')) {
                const index = $(e.target).parent().parent().index();
                const id = order.data[index].id;
                $.ajax({
                    url: '/order/delete',
                    type: 'DELETE',
                    data: {
                        id: id
                    },
                    success: result => {
                        if (result.code == 0) {
                            alert('删除成功');
                            // 当page的item全部被删除，需对page重新赋值
                            if (page > parseInt((count - 2) / size)) {
                                page = parseInt((count - 2) / size);
                                localStorage.todoPage = page;
                            }
                            fetchTodoOrders();
                        } else {
                            alert('删除失败：' + result.message);
                        }
                    }
                });
            }
        });
    }

    if (name) {
        fetchTodoOrdersByName(name);
    } else {
        fetchTodoOrders();
    }
};