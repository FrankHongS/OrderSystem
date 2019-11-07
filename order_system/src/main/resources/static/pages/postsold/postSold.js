$(
    function () {
        const selectedOrder = eval('(' + localStorage.selectedOrder + ')');

        const postsold = {};

        function bindOrder() {
            $('.name').text(selectedOrder.name);
            $('.phone-number').text(selectedOrder.phoneNumber);
            $('.address').text(selectedOrder.address);
            $('.sold-date').text(selectedOrder.soldDate);
            $('.machine-type').text(selectedOrder.machineType);
        }

        window.fetchPostSoldInfo = function () {
            $.ajax({
                url: '/postsold/query',
                data: {
                    orderId: selectedOrder.id,
                },
                success: result => {
                    if (result.code == 0) {
                        postsold.data = result.data;
                        buildPostSoldTable(
                            '.table-container tbody',
                            transformData(result.data)
                        );
                    } else {
                        console.error(result.message);
                    }
                },
                error: (xhr, e) => {
                    console.error(e);
                }
            });
        }

        function transformData(data) {
            const dataList = [];

            data.forEach((orderEle, index) => {
                const item = [];
                item.push(index + 1);
                item.push(orderEle.status);
                item.push(orderEle.todoDate);
                item.push(orderEle.comment);
                item.push('<img class="edit icon" src="../../images/edit.png" alt="编辑" srcset="" title="编辑">');
                dataList.push(item);
            });

            return dataList;
        }

        function buildPostSoldTable(container, dataList) {
            if (dataList.length == 0) {
                $(container).empty();
                return;
            }

            const cellsArray = dataList.map(
                rowValues => {
                    return rowValues.map(
                        (cell, index) => {
                            if (cell === '') {
                                return $('<td>')
                                    .text(cell);
                            }
                            if (index == 1) {
                                const status = getStatus(cell);
                                const span = $('<sapn>')
                                    .addClass(status.cls)
                                    .text(status.des);
                                return $('<td>')
                                    .append(span);
                            } else if (index == 4) {
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
        }

        function getStatus(statusCode) {
            const status = {};
            if (statusCode == 0) {
                status.cls = 'done';
                status.des = '已完成';
            } else if (statusCode == 1) {
                status.cls = 'todo';
                status.des = '待处理';
            } else if (statusCode == 2) {
                status.cls = 'skip';
                status.des = '不处理';
            }
            return status;
        }

        function bindTableClick() {
            $('.edit').on('click', function (e) {
                const index = $(e.target).parent().parent().index();
                window.postsold = postsold.data[index];
                layer.open({
                    type: 2,
                    title: '编辑售后详情',
                    area: ['860px', '480px'],
                    fix: false,
                    maxmin: false,
                    scrollbar: false,
                    content: 'detail/detail.html'
                });
            });
        }

        bindOrder();
        fetchPostSoldInfo();
    }
);