$(
    function () {

        const order = {};

        window.fetchOrders = function () {
            $.ajax({
                url: '/order/query',
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
            order.data = data;
            buildOrderTable('.table-container tbody', transformData(order.data));
        }

        function buildOrderTable(container, dataList) {

            const cellsArray = dataList.map(
                rowValues => {
                    return rowValues.map(
                        (cell, index) => {
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
            data.forEach((orderEle, index) => {
                const item = [];
                item.push(index + 1);
                item.push(orderEle.name);
                item.push(orderEle.phoneNumber);
                item.push(orderEle.address);
                item.push(orderEle.soldDate);
                item.push(orderEle.machineType);
                item.push('······');
                item.push('<img class="edit icon" src="../images/edit.png" alt="编辑" srcset="" title="编辑">' +
                    '<img class="delete icon" src="../images/delete.png" alt="删除" srcset="" title="删除">');
                dataList.push(item);
            });

            return dataList;
        }

        function bindClick() {
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
        }

        function bindTableClick() {
            $('.post-sold-detail').on('click', function (e) {
                alert('售后细节' + $(e.target).parent().index());
            });

            $('.delete').on('click', function (e) {
                if (confirm('删除该项')) {
                    const index = $(e.target).parent().parent().index();
                    const id = order.data[index].id;
                    console.log(id);
                    $.ajax({
                        url: '/order/delete',
                        type: 'DELETE',
                        data: {
                            id: id
                        },
                        success: result => {
                            if (result.code == 0) {
                                alert('删除成功');
                                order.data.splice(index, 1);
                                displayData(order.data);
                            } else {
                                alert('删除失败：' + result.message);
                            }
                        }
                    });
                }
            });
        }

        bindClick();
        fetchOrders();
    }
);