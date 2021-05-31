package ac.kr.smu.assembler;

import ac.kr.smu.controller.PostController;
import ac.kr.smu.domain.entity.Post;
import ac.kr.smu.resource.PostResource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostAssembler extends RepresentationModelAssemblerSupport<Post, PostResource> {
    public PostAssembler(){
        super(PostController.class, PostResource.class);
    }
    /*
    	entity를 resource 객체로 변환
    */
    @Override
    protected PostResource instantiateModel(Post entity){
        return new PostResource(entity);
    }
    /*
    	entity의 id로 링크를 만들고 resource객체로 반환
        EX) id 1의 post
        /post/1
    */
    @Override
    public PostResource toModel(Post entity) {
        return createModelWithId(entity.getId(), entity);
    }
    /*
    	entity 여러개를 한번에 변환하여 CollectionModel로 반환
    */
    @Override
    public CollectionModel<PostResource> toCollectionModel(Iterable<? extends Post> entities){
        List<PostResource> postList = new ArrayList<>();

        for(Post entity: entities)
            postList.add(toModel(entity));

        return CollectionModel.of(postList);
    }
}
