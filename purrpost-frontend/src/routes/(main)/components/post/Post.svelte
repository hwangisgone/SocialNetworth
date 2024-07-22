<script>
	import VerifiedIcon from '$lib/icon/verified.svg?component';
	export let postInfo = {
		post: {
			content: "Lorem ipsum dolor sit amet, consectetur",
			timePosted: "2024-06-18T01:27:33.5960633+08:00",
			timeEdited: null,
			likeCount: 0,
			replyCount: 0,
			shareCount: 0,
		},
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

	const postUser = postInfo.user;
	const post = postInfo.post;
	const profilePicUrl = postUser.avatarUrl ? postUser.avatarUrl : "https://i.imgur.com/xcLTrkV.png";

	import LikeButton from './LikeButton.svelte';
	import ReplyButton from './ReplyButton.svelte';
</script>


<!-- Post -->
<div
	class=" w-full border-2 border-t-0 border-base-300 cursor-pointer transition duration-350 ease-in-out pb-4 "
>
	<div class="flex flex-shrink-0 p-4 pb-0">
		<div href="/" class="flex-shrink-0 group block">
			<div class="flex items-top">
				<div>
					<img
						class="inline-block h-9 w-9 rounded-full object-cover"
						src={profilePicUrl}
						alt="User profile picture"
					/>
				</div>
				<div class="ml-3 flex gap-1 items-center font-medium">
					<a class="text-base-content" href={"/user/" + postUser.userId}>{postUser.name}</a>

					<VerifiedIcon class="text-teal-500" width="18" height="18" />
					<span class="text-sm text-gray-400  leading-5  group-hover:text-accent transition ease-in-out duration-150"
							>@{postUser.nameTag}</span>
					<span>·</span>
					<span class="text-sm text-gray-400 ">{toDisplayDate(post.timePosted)}</span>
				</div>
			</div>
		</div>
	</div>
	<div class="pl-16 pr-4">
		<p class="text-base-content width-auto font-medium flex-shrink whitespace-pre-line">
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
				<div class="pl-4"><LikeButton liked={postInfo.liked} postId={post.id} count={post.likeCount}/></div>
				<div class="pl-4"><ReplyButton postId={post.id} count={post.replyCount}/></div>
			</div>					
				
					
<!-- 					<div
						class="flex-1 flex items-center text-xs text-gray-400 hover:text-blue-400 dark:hover:text-blue-400 transition duration-350 ease-in-out"
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
