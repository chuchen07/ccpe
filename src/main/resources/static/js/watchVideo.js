
const dp = new DPlayer({
    container: document.getElementById('dplayer'),
    video: {
        url: $("a:eq(5)").attr("id"),
        autoplay: false,
        hotkey: true,
        type: 'auto',
        thumbnails: '/ChickenVideo/2.jpg',
        preload: 'auto'
    },
        danmaku: {
            id: $("a:eq(5)").text(),
            api: 'https://dplayer.moerats.com/',
            maximum: 1000,
            bottom: '15%',
            unlimited: true,
            addition: ['https://dplayer.moerats.com/v3/bilibili?aid=78551626']
        }
    }
);

function switchvideo (id) {
        dp.switchVideo({
            url: id,
            autoplay: false,
            hotkey: true,
            type: 'auto',
            preload: 'auto',
            thumbnails: '/ChickenVideo/3.jpg',
        },
            {
                id: id,
                api: 'https://dplayer.moerats.com/',
                maximum: 3000,
            });
}