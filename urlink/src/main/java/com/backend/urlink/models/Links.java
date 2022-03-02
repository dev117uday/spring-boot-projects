package com.backend.urlink.models;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_links")
@Builder
public class Links {

	@Id
	@GeneratedValue(
			strategy = GenerationType.IDENTITY
	)
	private Long linkId;
	private String urlLink;
	private String linkDescription;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "collection_id",
            referencedColumnName = "collectionId"
    )
    @ToString.Exclude
    private UrlCollections collection;

}
