$(
    function () {
        const postsold = parent.postsold;
        let status = postsold.status;

        function bindClick() {
            $('.status-wrapper input').on('change', function (e) {
                if ($(e.target).is(':checked')) {
                    status = $(e.target).parent().index();
                    $(e.target)
                        .parent().siblings().children('input')
                        .prop('checked', false);
                }
            });

            $('.button-save').on('click', function () {
                savePostSold();
            });

            $('.button-cancel').on('click', function () {
                closeFrame();
            });
        }

        function savePostSold() {
            $.ajax({
                url: '/postsold/update',
                type: 'POST',
                data: {
                    id: postsold.id,
                    status: status,
                    comment: $('.comment').val()
                },
                success: result => {
                    if (result.code == 0) {
                        closeFrame();
                        parent.fetchPostSoldInfo();
                    } else {
                        $('.message').text('保存失败: ' + result.message);
                    }
                },
                error: (xhr, e) => {
                    $('.message').text('保存失败');
                }
            });
        }

        function bindPostSold() {
            $('.todo-date').text(postsold.todoDate);
            const index = parseInt(postsold.status) + 1;
            $('.status div:nth-child(' + index + ')').children('input').prop('checked', true);
            $('.comment').text(postsold.comment);
        }

        function closeFrame() {
            const index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }

        bindPostSold();
        bindClick();
    }
);