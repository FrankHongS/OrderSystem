$(
    function () {
        $('.button-sure').on('click',function(){
            closeFrame();
        });

        $('.button-cancel').on('click', function () {
            closeFrame();
        });

        function closeFrame(){
            const index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    }
);