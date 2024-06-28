<script>
	import toast from 'svelte-french-toast';
	import PicsIcon	from '$lib/icon/pics.svg?component';
	import LocationIcon	from '$lib/icon/location-pin.svg?component';

	export let replyPost;
	let reply_modal;

		function toDisplayDate(datestr) {
		const date = new Date(datestr);
		return date.toLocaleDateString('en-US', { month: 'short', day: 'numeric', year: 'numeric' });
	}
</script>

<div
	class="flex-1 flex items-center text-gray-800 dark:text-white text-xs text-gray-400 hover:text-blue-400 dark:hover:text-blue-400 transition duration-350 ease-in-out"

	on:click={() => reply_modal.showModal()}
>
	<svg viewBox="0 0 24 24" fill="currentColor" class="w-5 h-5 mr-2"
		><g
			><path
				d="M14.046 2.242l-4.148-.01h-.002c-4.374 0-7.8 3.427-7.8 7.802 0 4.098 3.186 7.206 7.465 7.37v3.828c0 .108.044.286.12.403.142.225.384.347.632.347.138 0 .277-.038.402-.118.264-.168 6.473-4.14 8.088-5.506 1.902-1.61 3.04-3.97 3.043-6.312v-.017c-.006-4.367-3.43-7.787-7.8-7.788zm3.787 12.972c-1.134.96-4.862 3.405-6.772 4.643V16.67c0-.414-.335-.75-.75-.75h-.396c-3.66 0-6.318-2.476-6.318-5.886 0-3.534 2.768-6.302 6.3-6.302l4.147.01h.002c3.532 0 6.3 2.766 6.302 6.296-.003 1.91-.942 3.844-2.514 5.176z"
			></path></g
		></svg
	>
	0
</div>


<dialog bind:this={reply_modal} class="modal w-100">
	<!-- Press ESC key or click outside to close -->
	<form method="dialog" class="modal-backdrop">
        <button>close</button>
    </form>

    <div class="modal-box max-w-none max-h-none w-5/12 p-4 pt-12 bg-white dark:bg-gray-900 text-black dark:text-white">
        <form method="dialog" class="text-right">
            <button class="btn text-lg btn-sm btn-circle btn-ghost absolute right-2 top-2">✕</button>
        </form>

<div>
		<span class="text-gray-800">{replyPost.user.name}</span>
		<span class="text-sm text-gray-400  leading-5  group-hover:text-gray-300 transition ease-in-out duration-150"
				>@{replyPost.user.nameTag}</span>
		<span>·</span>
		<span class="text-sm text-gray-400 ">{toDisplayDate(replyPost.timePosted)}</span>
	<div class="pl-16">
		<p class="text-base width-auto font-medium text-gray-800 dark:text-white flex-shrink">
			{replyPost.content}
		</p>
	</div>
</div>


        <div class="flex gap-2">
            <div class="flex-shrink-0 w-10 h-10">
                <img class="w-full h-full rounded-full" src="https://i.imgur.com/GCBVgXD.jpeg" alt="" />
            </div>

            <div class="bg-gray-300 dark:bg-gray-700 w-full rounded-xl">
                <form class="w-full">
                    <textarea class="w-full h-52 text-black dark:text-white text-xl bg-gray-300 dark:bg-gray-700 rounded-xl p-2" placeholder="What's happening?"></textarea>
                </form>
<!--                 <div class="flex">
                    {#each inputData.imgQueue as img} 
                    <div class="relative flex-shrink-0 w-12 h-12 m-2">  
                        <img class="w-full h-full" src={img.path} alt="" />
                        <span class="absolute -right-2 -top-1 cursor-pointer" onclick="deleteImg('{img.path}')">
                            <svg width="22" height="22" viewBox="0 0 32 32" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" clip-rule="evenodd" d="M20.2544 9.66272L15.7278 14.1893L11.2012 9.66272C10.7014 9.16298 9.89028 9.16298 9.39054 9.66272C8.89034 10.1629 8.8908 10.9736 9.39054 11.4734L13.9172 16L9.39054 20.5266C8.89034 21.0268 8.8908 21.8375 9.39054 22.3373C9.89028 22.837 10.701 22.8375 11.2012 22.3373L15.7278 17.8107L20.2544 22.3373C20.7542 22.837 21.5649 22.8375 22.0651 22.3373C22.5648 21.8375 22.5648 21.0264 22.0651 20.5266L17.5385 16L22.0651 11.4734C22.5648 10.9736 22.5648 10.1625 22.0651 9.66272C21.5654 9.16298 20.7542 9.16298 20.2544 9.66272ZM26.5917 26.8639C20.5917 32.864 10.864 32.864 4.86391 26.8639C-1.13614 20.8639 -1.13614 11.1361 4.86391 5.13609C10.8635 -0.863501 20.5917 -0.863954 26.5917 5.13609C32.5918 11.1361 32.5913 20.8643 26.5917 26.8639Z" fill="white"/>
                            </svg>
                        </span>
                    </div>
                    {/each}
                </div> -->
            </div>
        </div>

        <div class="flex pt-4 pl-4 items-center justify-between text-black dark:text-white">
            <div class="flex gap-4">
                <PicsIcon width=24 height=24 fill="currentColor"/>
                <LocationIcon width=24 height=24 fill="currentColor"/>			
            </div>
            <div class="flex">
                
                <div class="relative flex-shrink-0 w-10/12 px-2 text-black dark:text-white absolute -right-2">
                    <button class="w-40 p-2 bg-blue-500 hover:bg-opacity-40 rounded-full font-bold border border-gray-700 ">
                        Reply </button>
                </div>
            </div>
        </div>

    </div>

</dialog>