<script>
	export let post = {
		content: "Lorem ipsum dolor sit amet, consectetur",
		timePosted: "2024-06-18T01:27:33.5960633+08:00",
		timeEdited: null,
		likeCount: 0,
		replyCount: 0,
		shareCount: 0,

				// Extra
		liked: false,
		user: {
			"userId": 1,
			"nameTag": "giant",
			"password": "troll",
			"name": "Giant",
			"email": "giant@example.com",
			"bio": "Bio of Giant",
			"role": "user",
			"phone": "123-456-7890",
			"registrationDate": "2024-05-15T00:00:00.000+00:00"
		}
	}

	function toDisplayDate(datestr) {
		const date = new Date(datestr);
		return date.toLocaleDateString('en-US', { month: 'short', day: 'numeric', year: 'numeric' });
	}

	const postUser = post.user;

	import LikeButton from './LikeButton.svelte';
	import ReplyButton from './ReplyButton.svelte';
</script>


<!-- Post -->
<div
	class=" w-full border-b border-gray-200 dark:border-gray-200 hover:bg-gray-400 dark:hover:bg-gray-600 cursor-pointer transition duration-350 ease-in-out pb-4 border-l border-r"
>
	<div class="flex flex-shrink-0 p-4 pb-0">
		<a href="/" class="flex-shrink-0 group block"
			><div class="flex items-top">
				<div>
					<img
						class="inline-block h-9 w-9 rounded-full"
						src="https://i.imgur.com/GCBVgXD.jpeg"
						alt=""
					/>
				</div>
				<div class="ml-3 flex gap-1 items-center font-medium">
					<a class="text-gray-800" href={"/user/" + postUser.userId}>{postUser.name}</a>
						<!-- TODO -->
						<svg viewBox="0 0 24 24"
								aria-label="Verified account"
								fill="currentColor"
								class="w-4 h-4 ml-1 text-blue-500 dark:text-white">
							<g><path
								d="M22.5 12.5c0-1.58-.875-2.95-2.148-3.6.154-.435.238-.905.238-1.4 0-2.21-1.71-3.998-3.818-3.998-.47 0-.92.084-1.336.25C14.818 2.415 13.51 1.5 12 1.5s-2.816.917-3.437 2.25c-.415-.165-.866-.25-1.336-.25-2.11 0-3.818 1.79-3.818 4 0 .494.083.964.237 1.4-1.272.65-2.147 2.018-2.147 3.6 0 1.495.782 2.798 1.942 3.486-.02.17-.032.34-.032.514 0 2.21 1.708 4 3.818 4 .47 0 .92-.086 1.335-.25.62 1.334 1.926 2.25 3.437 2.25 1.512 0 2.818-.916 3.437-2.25.415.163.865.248 1.336.248 2.11 0 3.818-1.79 3.818-4 0-.174-.012-.344-.033-.513 1.158-.687 1.943-1.99 1.943-3.484zm-6.616-3.334l-4.334 6.5c-.145.217-.382.334-.625.334-.143 0-.288-.04-.416-.126l-.115-.094-2.415-2.415c-.293-.293-.293-.768 0-1.06s.768-.294 1.06 0l1.77 1.767 3.825-5.74c.23-.345.696-.436 1.04-.207.346.23.44.696.21 1.04z"
							></path></g>
						</svg>
					<span class="text-sm text-gray-400  leading-5  group-hover:text-gray-300 transition ease-in-out duration-150"
							>@{postUser.nameTag}</span>
					<span>·</span>
					<span class="text-sm text-gray-400 ">{toDisplayDate(post.timePosted)}</span>
				</div>
			</div></a
		>
	</div>
	<div class="pl-16">
		<p class="text-base width-auto font-medium text-gray-800 dark:text-white flex-shrink">
			{post.content}
		</p>
<!-- 		<div
			class="columns-1 gap-5 sm:columns-2 sm:gap-8 md:columns-3 lg:columns-2 [&amp;>img:not(:first-child)]:mt-8"
		>
			<div class="flex my-3 mr-2 rounded-2xl border border-gray-600">
				<video class="rounded-2xl"
					><source src="https://i.imgur.com/okXWY5w.mp4" type="video/mp4" />
					Your browser does not support the video tag.</video
				>
			</div>
			<div class="flex my-3 mr-2 rounded-2xl border border-gray-600">
				<img class="rounded-2xl" src="https://i.imgur.com/DBpznrn.jpeg" alt="" />
			</div>
			<div class="flex my-3 mr-2 rounded-2xl border border-gray-600">
				<img class="rounded-2xl" src="https://i.imgur.com/ISpNf4j.jpeg" alt="" />
			</div>
			<div class="flex my-3 mr-2 rounded-2xl border border-gray-600">
				<img class="rounded-2xl" src="https://i.imgur.com/fjXFX93.jpeg" alt="" />
			</div>
		</div> -->
		<div class="flex pt-5">

			<div class="grid grid-cols-4 gap-4">
				<div class="pl-4"><LikeButton liked={post.liked} postId={post.id} count={post.likeCount}/></div>
				<div class="pl-4"><ReplyButton /></div>
			</div>					
				
					
<!-- 					<div
						class="flex-1 flex items-center text-gray-800 dark:text-white text-xs text-gray-400 hover:text-blue-400 dark:hover:text-blue-400 transition duration-350 ease-in-out"
					>
						<svg viewBox="0 0 24 24" fill="currentColor" class="w-5 h-5 mr-2"
							><g
								><path
									d="M17.53 7.47l-5-5c-.293-.293-.768-.293-1.06 0l-5 5c-.294.293-.294.768 0 1.06s.767.294 1.06 0l3.72-3.72V15c0 .414.336.75.75.75s.75-.336.75-.75V4.81l3.72 3.72c.146.147.338.22.53.22s.384-.072.53-.22c.293-.293.293-.767 0-1.06z"
								></path><path
									d="M19.708 21.944H4.292C3.028 21.944 2 20.916 2 19.652V14c0-.414.336-.75.75-.75s.75.336.75.75v5.652c0 .437.355.792.792.792h15.416c.437 0 .792-.355.792-.792V14c0-.414.336-.75.75-.75s.75.336.75.75v5.652c0 1.264-1.028 2.292-2.292 2.292z"
								></path></g
							></svg
						>
					</div> -->


		</div>
	</div>
</div>
<!-- /Post -->
