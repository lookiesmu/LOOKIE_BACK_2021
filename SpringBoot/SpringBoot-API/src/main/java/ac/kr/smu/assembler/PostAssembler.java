package ac.kr.smu.assembler;

import ac.kr.smu.controller.PostController;
import ac.kr.smu.resource.PostResource;
import ac.kr.smu.domain.Post;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostAssembler extends RepresentationModelAssemblerSupport<Post, PostResource> {
    public PostAssembler(){
        super(PostController.class,PostResource.class);
    }

    @Override
    protected PostResource instantiateModel(Post entity) {
        return new PostResource(entity);
    }

    @Override
    public PostResource toModel(Post entity) {
        return createModelWithId(entity.getId(),entity);
    }

    @Override
    public CollectionModel<PostResource> toCollectionModel(Iterable<? extends Post> entities) {
        List<PostResource> postList = new ArrayList<>();

        for(Post entity : entities)
            postList.add(toModel(entity));

        return CollectionModel.of(postList);
    }

}
