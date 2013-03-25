/**
 * Copyright 2010 Tommi S.E. Laukkanen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vaadin.addons.lazyquerycontainer;

import com.vaadin.data.util.BeanItem;

import javax.persistence.EntityManager;

/**
 * EntityContainer enables loading JPA entities in non lazy manner in single read operation
 * using same query backend as LCQ.
 *
 * @param <T> Entity class.
 * @author Tommi Laukkanen
 */
public final class EntityContainer<T> extends LazyQueryContainer {
    /**
     * Java serialization version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor which configures query definition for accessing JPA entities.
     *
     * @param entityManager                  The JPA EntityManager.
     * @param entityClass                    The entity class.
     * @param idPropertyId                   The ID of the ID property or null if item index is used as ID.
     * @param maximumQueryResultSize         The maximum size of the query result.
     * @param applicationManagedTransactions True if application manages transactions instead of container.
     * @param detachedEntities               True if entities are detached from PersistenceContext.
     * @param compositeItems                 True f items are wrapped to CompositeItems.
     */
    public EntityContainer(final EntityManager entityManager,
                           final Class<?> entityClass,
                           final Object idPropertyId,
                           final int maximumQueryResultSize,
                           final boolean applicationManagedTransactions,
                           final boolean detachedEntities, final boolean compositeItems) {
        super(new EntityQueryDefinition(applicationManagedTransactions,
                detachedEntities, compositeItems,
                entityClass, maximumQueryResultSize, idPropertyId),
                new EntityQueryFactory(entityManager));
        getQueryView().getQueryDefinition().setMaxQuerySize(maximumQueryResultSize);
    }


}
